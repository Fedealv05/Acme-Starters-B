
package acme.features.member.campaign;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.campaigns.Campaign;
import acme.realms.Member;

@Controller
public class MemberCampaignController extends AbstractController<Member, Campaign> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", MemberCampaignListService.class);
		super.addBasicCommand("show", MemberCampaignShowService.class);

	}
}
