
package acme.features.manager.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.entities.inventions.Invention;
import acme.entities.projects.Project;
import acme.entities.strategy.Strategy;
import acme.features.manager.campaign.ManagerCampaignRepository;
import acme.features.manager.invention.ManagerInventionRepository;
import acme.features.manager.strategy.ManagerStrategyRepository;
import acme.realms.Manager;

@Service
public class ManagerProjectDeleteService extends AbstractService<Manager, Project> {

	@Autowired
	private ManagerProjectRepository	repository;

	@Autowired
	private ManagerInventionRepository	inventionRepository;

	@Autowired
	private ManagerCampaignRepository	campaignRepository;

	@Autowired
	private ManagerStrategyRepository	strategyRepository;

	private Project						project;


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.project = this.repository.findProjectById(id);
	}

	@Override
	public void authorise() {
		boolean status = true;

		if (this.project != null) {

			boolean createdByThePrincipal;
			createdByThePrincipal = this.project.getManager().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();

			status = createdByThePrincipal && this.project.getDraftMode();
		} else
			status = false;

		super.setAuthorised(status);
	}

	@Override
	public void execute() {

		//CUANDO ESTÉ PROJECTMEMBER HAY QUE CAMBIAR ESTE METODO PARA DESVINCULARLOS TAMBIÉN
		List<Invention> inventions;
		List<Campaign> campaigns;
		List<Strategy> strategies;

		int id;
		id = super.getRequest().getData("id", int.class);

		inventions = this.inventionRepository.findByProjectId(id);

		inventions.forEach(inv -> {
			inv.setProject(null);
			this.inventionRepository.save(inv);
		});

		campaigns = this.campaignRepository.findByProjectId(id);

		campaigns.forEach(camp -> {
			camp.setProject(null);
			this.campaignRepository.save(camp);
		});

		strategies = this.strategyRepository.findByProjectId(id);

		strategies.forEach(stra -> {
			stra.setProject(null);
			this.strategyRepository.save(stra);
		});

		this.repository.delete(this.project);
	}
	@Override
	public void validate() {
	}

	@Override
	public void bind() {
		super.bindObject(this.project, "title", "keyWords", "description", "kickOff", "closeOut");
	}

	@Override
	public void unbind() {
		super.unbindObject(this.project, "title", "keyWords", "description", "kickOff", "closeOut", "draftMode", "effort");
		super.unbindGlobal("managerId", this.project.getManager().getId());
	}

}
