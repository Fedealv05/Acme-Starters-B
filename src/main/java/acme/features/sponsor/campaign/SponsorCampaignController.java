
package acme.features.sponsor.campaign;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.campaigns.Campaign;
import acme.realms.Sponsor;

@Controller
public class SponsorCampaignController extends AbstractController<Sponsor, Campaign> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", SponsorCampaignListService.class);
		super.addBasicCommand("show", SponsorCampaignShowService.class);

	}
}
