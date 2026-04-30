
package acme.features.manager.dashboard;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.forms.ManagerDashboard;
import acme.realms.Manager;

@Service
public class ManagerDashboardShowService extends AbstractService<Manager, ManagerDashboard> {

	@Autowired
	private ManagerDashboardRepository	repository;

	private ManagerDashboard			dashboard;


	@Override
	public void load() {
		int managerId = super.getRequest().getPrincipal().getActiveRealm().getId();
		this.dashboard = new ManagerDashboard();

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
				double effort = p.getEffort();

				if (effort < min)
					min = effort;
				if (effort > max)
					max = effort;
				sum += effort;
			}

			avg = sum / projects.size();

			double sumOfSquares = 0.0;
			for (Project p : projects) {
				double effort = p.getEffort();
				sumOfSquares += Math.pow(effort - avg, 2);
			}

			dev = Math.sqrt(sumOfSquares / projects.size());
		}

		this.dashboard.setMinEffort(min);
		this.dashboard.setMaxEffort(max);
		this.dashboard.setAvgEffort(avg);
		this.dashboard.setDevEffort(dev);
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
