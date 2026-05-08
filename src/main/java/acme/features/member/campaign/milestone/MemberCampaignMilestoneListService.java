
package acme.features.member.campaign.milestone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaigns.Milestone;
import acme.realms.Member;

@Service
public class MemberCampaignMilestoneListService extends AbstractService<Member, Milestone> {

	@Autowired
	private MemberCampaignMilestoneRepository	repository;

	private List<Milestone>						milestones;
	private int									campaignId;
	private int									projectId;


	@Override
	public void load() {
		this.campaignId = super.getRequest().getData("campaignId", int.class);
		this.milestones = this.repository.findByCampaignId(this.campaignId);
		if (this.repository.findCampaignById(this.campaignId) != null)
			this.projectId = this.repository.findCampaignById(this.campaignId).getProject().getId();
	}

	@Override
	public void authorise() {
		boolean status;
		int memberId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = this.repository.findProjectById(this.projectId) != null && this.repository.findProjectMember(this.projectId, memberId) != null;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.milestones, "title", "effort", "kind");
	}

}
