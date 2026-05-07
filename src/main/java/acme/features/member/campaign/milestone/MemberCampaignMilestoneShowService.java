
package acme.features.member.campaign.milestone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaigns.Milestone;
import acme.realms.Member;

@Service
public class MemberCampaignMilestoneShowService extends AbstractService<Member, Milestone> {

	@Autowired
	private MemberCampaignMilestoneRepository	repository;

	private Milestone							milestone;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.milestone = this.repository.findMilestoneById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int memberId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = this.milestone != null && this.milestone.getCampaign().getProject() != null && this.repository.findProjectMember(this.milestone.getCampaign().getProject().getId(), memberId) != null;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.milestone, "title", "achievements", "effort", "kind");
	}

}
