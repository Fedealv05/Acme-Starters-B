
package acme.features.member.campaign.milestone;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.campaigns.Milestone;
import acme.realms.Member;

@Controller
public class MemberCampaignMilestoneController extends AbstractController<Member, Milestone> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", MemberCampaignMilestoneListService.class);
		super.addBasicCommand("show", MemberCampaignMilestoneShowService.class);

	}
}
