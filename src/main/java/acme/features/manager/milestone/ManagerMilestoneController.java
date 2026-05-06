
package acme.features.manager.milestone;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.campaigns.Milestone;
import acme.realms.Manager;

@Controller
public class ManagerMilestoneController extends AbstractController<Manager, Milestone> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", ManagerMilestoneListService.class);
		super.addBasicCommand("show", ManagerMilestoneShowService.class);

	}
}
