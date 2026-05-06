
package acme.features.spokesperson.campaign_assignment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.entities.projects.Project;
import acme.forms.CampaignAssignment;
import acme.realms.Spokesperson;

@Service
public class SpokespersonCampaignAssignmentCreateService extends AbstractService<Spokesperson, CampaignAssignment> {

	@Autowired
	private SpokespersonCampaignAssignmentRepository	repository;

	private CampaignAssignment							campaignAssignment;
	private List<Campaign>								campaigns;
	private Project										project;


	@Override
	public void load() {
		int projectId = super.getRequest().getData("projectId", int.class);
		this.project = this.repository.findProjectById(projectId);
		int spokespersonId = super.getRequest().getPrincipal().getActiveRealm().getId();
		this.campaigns = this.repository.findAvailableCampaignsBySpokespersonId(spokespersonId);
		this.campaignAssignment = super.newObject(CampaignAssignment.class);
		this.campaignAssignment.setProjectId(projectId);
	}

	@Override
	public void authorise() {
		boolean status = false;

		int accountId = super.getRequest().getPrincipal().getAccountId();

		if (this.project != null) {

			Integer count = this.repository.checkProjectBelongsToAccountId(this.project.getId(), accountId);

			status = count != null && count > 0;
		}

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		int projectId = super.getRequest().getData("projectId", int.class);
		super.bindObject(this.campaignAssignment, "campaignId");
		this.campaignAssignment.setProjectId(projectId);
	}

	@Override
	public void validate() {
		super.validateObject(this.campaignAssignment);
	}

	@Override
	public void execute() {
		Campaign campaign = this.repository.findCampaignById(this.campaignAssignment.getCampaignId());
		if (campaign != null) {
			campaign.setProject(this.project);
			this.repository.save(campaign);
		}
	}

	@Override
	public void unbind() {
		SelectChoices choices;
		choices = SelectChoices.from(this.campaigns, "ticker", null);
		super.unbindObject(this.campaignAssignment, "campaignId");
		super.unbindGlobal("listaCampaigns ", choices);
		super.unbindGlobal("projectId", this.project.getId());
	}

}
