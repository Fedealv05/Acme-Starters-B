
package acme.features.auditor.invention;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.inventions.Invention;
import acme.realms.Auditor;

@Controller
public class AuditorInventionController extends AbstractController<Auditor, Invention> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", AuditorInventionListService.class);
		super.addBasicCommand("show", AuditorInventionShowService.class);

	}
}
