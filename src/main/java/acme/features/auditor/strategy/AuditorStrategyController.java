
package acme.features.auditor.strategy;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.strategy.Strategy;
import acme.realms.Auditor;

@Controller
public class AuditorStrategyController extends AbstractController<Auditor, Strategy> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", AuditorStrategyListService.class);
		super.addBasicCommand("show", AuditorStrategyShowService.class);

	}
}
