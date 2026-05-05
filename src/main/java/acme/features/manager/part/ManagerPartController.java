
package acme.features.manager.part;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.inventions.Part;
import acme.realms.Manager;

@Controller
public class ManagerPartController extends AbstractController<Manager, Part> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", ManagerPartListService.class);
		super.addBasicCommand("show", ManagerPartShowService.class);

	}
}
