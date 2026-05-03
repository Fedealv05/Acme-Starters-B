
package acme.features.manager.strategy;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.strategy.Strategy;
import acme.realms.Manager;

@Controller
public class ManagerStrategyController extends AbstractController<Manager, Strategy> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", ManagerStrategyListService.class);
		super.addBasicCommand("show", ManagerStrategyShowService.class);

	}
}
