
package acme.features.sponsor.strategy;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.strategy.Strategy;
import acme.realms.Sponsor;

@Controller
public class SponsorStrategyController extends AbstractController<Sponsor, Strategy> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", SponsorStrategyListService.class);
		super.addBasicCommand("show", SponsorStrategyShowService.class);

	}
}
