
package acme.features.sponsor.sponsorship_project;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.forms.SponsorshipProject;
import acme.realms.Sponsor;

@Controller
public class SponsorshipProjectController extends AbstractController<Sponsor, SponsorshipProject> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("create", SponsorshipProjectCreateService.class);

		super.addCustomCommand("unassign", "update", SponsorshipProjectUnassignService.class);
	}

}
