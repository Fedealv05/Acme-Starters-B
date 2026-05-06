
package acme.features.sponsor.invention;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.inventions.Invention;
import acme.realms.Sponsor;

@Controller
public class SponsorInventionController extends AbstractController<Sponsor, Invention> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", SponsorInventionListService.class);
		super.addBasicCommand("show", SponsorInventionShowService.class);

	}
}
