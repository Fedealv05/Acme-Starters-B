
package acme.features.manager.spokesperson;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.realms.Manager;
import acme.realms.Spokesperson;

@Controller
public class ManagerSpokespersonController extends AbstractController<Manager, Spokesperson> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", ManagerSpokespersonListService.class);
		super.addBasicCommand("show", ManagerSpokespersonShowService.class);

	}
}
