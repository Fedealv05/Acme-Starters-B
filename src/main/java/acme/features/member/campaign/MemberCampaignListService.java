
package acme.features.member.campaign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.realms.Member;

@Service
public class MemberCampaignListService extends AbstractService<Member, Campaign> {

	@Autowired
	private MemberCampaignRepository	repository;

	private List<Campaign>				campaigns;
	private int							projectId;


	@Override
	public void load() {
		this.projectId = super.getRequest().getData("projectId", int.class);
		this.campaigns = this.repository.findCampaignsByProjectId(this.projectId);
	}

	@Override
	public void authorise() {
		boolean status;
		int memberId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = this.repository.findProjectMember(this.projectId, memberId) != null;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.campaigns, "ticker", "name", "startMoment", "endMoment", "draftMode");
	}

}
