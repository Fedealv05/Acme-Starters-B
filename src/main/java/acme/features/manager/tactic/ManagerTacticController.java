
package acme.features.manager.tactic;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.strategy.Tactic;
import acme.realms.Manager;

@Controller
public class ManagerTacticController extends AbstractController<Manager, Tactic> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", ManagerTacticListService.class);
		super.addBasicCommand("show", ManagerTacticShowService.class);

	}
}
