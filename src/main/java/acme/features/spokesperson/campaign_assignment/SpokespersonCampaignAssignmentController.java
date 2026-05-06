
package acme.features.spokesperson.campaign_assignment;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.forms.CampaignAssignment;
import acme.realms.Spokesperson;

@Controller
public class SpokespersonCampaignAssignmentController extends AbstractController<Spokesperson, CampaignAssignment> {

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("create", SpokespersonCampaignAssignmentCreateService.class);
	}

}
