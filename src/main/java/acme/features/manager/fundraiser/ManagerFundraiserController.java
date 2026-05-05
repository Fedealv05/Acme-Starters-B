
package acme.features.manager.fundraiser;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.realms.Fundraiser;
import acme.realms.Manager;

@Controller
public class ManagerFundraiserController extends AbstractController<Manager, Fundraiser> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", ManagerFundraiserListService.class);
		super.addBasicCommand("show", ManagerFundraiserShowService.class);

	}
}
