
package acme.features.inventor.invention_assignment;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.forms.InventionAssignment;
import acme.realms.Inventor;

@Controller
public class InventorInventionAssignmentController extends AbstractController<Inventor, InventionAssignment> {

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("create", InventorInventionAssignmentCreateService.class);
	}

}
