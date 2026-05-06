
package acme.features.member.invention.part;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.inventions.Part;
import acme.realms.Member;

@Controller
public class MemberInventionPartController extends AbstractController<Member, Part> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", MemberInventionPartListService.class);
		super.addBasicCommand("show", MemberInventionPartShowService.class);

	}

}
