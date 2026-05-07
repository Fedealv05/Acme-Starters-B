
package acme.features.auditor.campaign;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.campaigns.Campaign;
import acme.realms.Auditor;

@Controller
public class AuditorCampaignController extends AbstractController<Auditor, Campaign> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", AuditorCampaignListService.class);
		super.addBasicCommand("show", AuditorCampaignShowService.class);

	}
}
