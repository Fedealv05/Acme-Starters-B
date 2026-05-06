
package acme.features.member.spokesperson;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.realms.Member;
import acme.realms.Spokesperson;

@Controller
public class MemberSpokespersonController extends AbstractController<Member, Spokesperson> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", MemberSpokespersonListService.class);
		super.addBasicCommand("show", MemberSpokespersonShowService.class);
	}

}
