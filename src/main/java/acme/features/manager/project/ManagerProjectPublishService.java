
package acme.features.manager.project;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.helpers.MomentHelper;
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
public class ManagerProjectPublishService extends AbstractService<Manager, Project> {

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
	public void bind() {
		super.bindObject(this.project, "title", "keyWords", "description", "kickOff", "closeOut");
	}

	@Override
	public void validate() {
		super.validateObject(this.project);

		boolean hasAtLeastOneInvention;
		List<Invention> inventions = this.inventionRepository.findByProjectId(this.project.getId());
		hasAtLeastOneInvention = inventions.size() >= 1;

		super.state(hasAtLeastOneInvention, "*", "project.publish.validation.hasAtLeastOneInvention");

		boolean validKickOff;
		Date publishMoment = MomentHelper.getCurrentMoment();
		validKickOff = MomentHelper.isAfter(this.project.getKickOff(), publishMoment);

		super.state(validKickOff, "kickOff", "project.publish.validation.validKickOff");

		boolean validCloseOut;
		validCloseOut = MomentHelper.isAfter(this.project.getCloseOut(), publishMoment);

		super.state(validCloseOut, "endMoment", "project.publish.validation.validCloseOut");

	}

	@Override
	public void execute() {
		List<Invention> inventions;
		List<Campaign> campaigns;
		List<Strategy> strategies;

		int id;
		id = super.getRequest().getData("id", int.class);

		inventions = this.inventionRepository.findByProjectId(id);

		inventions.forEach(inv -> {
			inv.setDraftMode(false);
			this.inventionRepository.save(inv);
		});

		campaigns = this.campaignRepository.findByProjectId(id);

		campaigns.forEach(camp -> {
			camp.setDraftMode(false);
			this.campaignRepository.save(camp);
		});

		strategies = this.strategyRepository.findByProjectId(id);

		strategies.forEach(stra -> {
			stra.setDraftMode(false);
			this.strategyRepository.save(stra);
		});

		this.project.setDraftMode(false);
		this.repository.save(this.project);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.project, "title", "keyWords", "description", "kickOff", "closeOut", "draftMode", "effort");
		super.unbindGlobal("managerId", this.project.getManager().getId());
	}

}
