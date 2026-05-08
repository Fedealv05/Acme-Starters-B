
package acme.features.manager.milestone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.entities.campaigns.Milestone;
import acme.entities.projects.Project;
import acme.features.spokesperson.campaign.SpokespersonCampaignRepository;
import acme.realms.Manager;

@Service
public class ManagerMilestoneListService extends AbstractService<Manager, Milestone> {

	@Autowired
	private ManagerMilestoneRepository		repository;

	@Autowired
	private SpokespersonCampaignRepository	campaignRepository;

	private List<Milestone>					milestones;

	private Campaign						campaign;

	private Project							project;


	@Override
	public void load() {

		int id = this.getRequest().getData("campaignId", int.class);

		this.milestones = this.repository.findByCampaignId(id);
		this.campaign = this.campaignRepository.findCampaignById(id);
		if (this.campaign != null)
			this.project = this.campaign.getProject();
	}

	@Override
	public void authorise() {
		boolean status = this.campaign != null && this.project != null && this.project.getManager().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.milestones, "title", "effort", "kind");
		super.unbindGlobal("draftMode", this.campaign.getDraftMode());
		super.unbindGlobal("campaignId", this.campaign.getId());

	}

}
