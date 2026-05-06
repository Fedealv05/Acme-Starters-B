
package acme.features.fundraiser.strategy_assignment;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.forms.StrategyAssignment;
import acme.realms.Fundraiser;

@Controller
public class FundraiserStrategyAssignmentController extends AbstractController<Fundraiser, StrategyAssignment> {

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("create", FundraiserStrategyAssignmentCreateService.class);
	}

}
