
package acme.features.member.invention.part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.inventions.Part;
import acme.realms.Member;

@Service
public class MemberInventionPartShowService extends AbstractService<Member, Part> {

	@Autowired
	private MemberInventionPartRepository	repository;

	private Part							part;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.part = this.repository.findPartById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int memberId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = this.part != null && this.part.getInvention().getProject() != null && this.repository.findProjectMember(this.part.getInvention().getProject().getId(), memberId) != null;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.part, "name", "description", "cost", "kind");
	}

}
