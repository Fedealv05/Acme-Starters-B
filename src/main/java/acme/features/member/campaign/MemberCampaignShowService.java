
package acme.features.member.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.realms.Member;

@Service
public class MemberCampaignShowService extends AbstractService<Member, Campaign> {

	@Autowired
	private MemberCampaignRepository	repository;

	private Campaign					campaign;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.campaign = this.repository.findCampaignById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int memberId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = this.campaign != null && this.campaign.getProject() != null && this.repository.findProjectMember(this.campaign.getProject().getId(), memberId) != null;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.campaign, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode");
		super.unbindGlobal("spokespersonName", this.campaign.getSpokesperson().getUserAccount().getIdentity().getFullName());
	}

}
