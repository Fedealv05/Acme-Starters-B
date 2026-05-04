
package acme.features.manager.dashboard;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.entities.campaigns.CampaignRepository;
import acme.entities.inventions.Invention;
import acme.entities.inventions.InventionRepository;
import acme.entities.projects.Project;
import acme.entities.strategy.Strategy;
import acme.entities.strategy.StrategyRepository;
import acme.forms.ManagerDashboard;
import acme.realms.Manager;

@Service
public class ManagerDashboardShowService extends AbstractService<Manager, ManagerDashboard> {

	@Autowired
	private ManagerDashboardRepository	repository;

	@Autowired
	private InventionRepository			inventionRepository;

	@Autowired
	private CampaignRepository			campaignRepository;

	@Autowired
	private StrategyRepository			strategyRepository;

	private ManagerDashboard			dashboard;


	@Override
	public void load() {
		int managerId = super.getRequest().getPrincipal().getActiveRealm().getId();
		this.dashboard = super.newObject(ManagerDashboard.class);

		this.dashboard.setTotalProjects(this.repository.totalProjects(managerId));
		this.dashboard.setDevTotalProjects(this.repository.devTotalProjects());

		Collection<Project> projects = this.repository.findProjectsByManagerId(managerId);

		double min = 0.0;
		double max = 0.0;
		double avg = 0.0;
		double dev = 0.0;

		if (!projects.isEmpty()) {
			min = Double.MAX_VALUE;
			max = Double.MIN_VALUE;
			double sum = 0.0;

			for (Project p : projects) {
				double effort = this.computeEffort(p);

				if (effort < min)
					min = effort;
				if (effort > max)
					max = effort;
				sum += effort;
			}

			avg = sum / projects.size();

			double sumOfSquares = 0.0;
			for (Project p : projects) {
				double effort = this.computeEffort(p);
				sumOfSquares += Math.pow(effort - avg, 2);
			}

			dev = Math.sqrt(sumOfSquares / projects.size());
		}

		this.dashboard.setMinEffort(min);
		this.dashboard.setMaxEffort(max);
		this.dashboard.setAvgEffort(avg);
		this.dashboard.setDevEffort(dev);
	}

	private double computeEffort(final Project project) {
		double totalMonths = 0.0;
		Set<Integer> uniqueMemberIds = new HashSet<>();

		Collection<Invention> inventions = this.inventionRepository.findByProjectId(project.getId());
		if (inventions != null)
			for (Invention inv : inventions) {
				totalMonths += inv.getMonthsActive();
				uniqueMemberIds.add(inv.getInventor().getId());
			}

		Collection<Campaign> campaigns = this.campaignRepository.findByProjectId(project.getId());
		if (campaigns != null)
			for (Campaign cam : campaigns) {
				totalMonths += cam.getMonthsActive();
				uniqueMemberIds.add(cam.getSpokesperson().getId());
			}

		Collection<Strategy> strategies = this.strategyRepository.findByProjectId(project.getId());
		if (strategies != null)
			for (Strategy str : strategies) {
				totalMonths += str.getMonthsActive();
				uniqueMemberIds.add(str.getFundraiser().getId());
			}

		int numberOfPeople = uniqueMemberIds.size();

		if (numberOfPeople == 0)
			return 0.0;

		return totalMonths / numberOfPeople;
	}

	@Override
	public void authorise() {
		boolean status = super.getRequest().getPrincipal().hasRealmOfType(Manager.class);
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.dashboard, "totalProjects", "devTotalProjects", "minEffort", "maxEffort", "avgEffort", "devEffort");
	}
}
