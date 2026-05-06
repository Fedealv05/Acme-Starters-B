
package acme.features.member.inventor;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.realms.Inventor;
import acme.realms.Member;

@Controller
public class MemberInventorController extends AbstractController<Member, Inventor> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", MemberInventorListService.class);
		super.addBasicCommand("show", MemberInventorShowService.class);
	}
}
