
package acme.features.manager.campaign;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.campaigns.Campaign;
import acme.realms.Manager;

@Controller
public class ManagerCampaignController extends AbstractController<Manager, Campaign> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", ManagerCampaignListService.class);
		super.addBasicCommand("show", ManagerCampaignShowService.class);

	}

}
