
package acme.features.manager.inventor;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.realms.Inventor;
import acme.realms.Manager;

@Controller
public class ManagerInventorController extends AbstractController<Manager, Inventor> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", ManagerInventorListService.class);
		super.addBasicCommand("show", ManagerInventorShowService.class);

	}
}
