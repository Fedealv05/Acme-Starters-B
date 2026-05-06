
package acme.features.member.strategy.tactic;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.strategy.Tactic;
import acme.realms.Member;

@Controller
public class MemberStrategyTacticController extends AbstractController<Member, Tactic> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", MemberStrategyTacticListService.class);
		super.addBasicCommand("show", MemberStrategyTacticShowService.class);

	}

}
