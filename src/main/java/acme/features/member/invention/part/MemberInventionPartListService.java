
package acme.features.member.invention.part;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.inventions.Part;
import acme.realms.Member;

@Service
public class MemberInventionPartListService extends AbstractService<Member, Part> {

	@Autowired
	private MemberInventionPartRepository	repository;

	private List<Part>						parts;
	private int								inventionId;
	private int								projectId;


	@Override
	public void load() {
		this.inventionId = super.getRequest().getData("inventionId", int.class);
		this.parts = this.repository.findByInventionId(this.inventionId);
		if (this.repository.findInventionById(this.inventionId) != null)
			this.projectId = this.repository.findInventionById(this.inventionId).getProject().getId();
	}

	@Override
	public void authorise() {
		boolean status;
		int memberId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = this.repository.findProjectById(this.projectId) != null && this.repository.findProjectMember(this.projectId, memberId) != null;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.parts, "name", "cost", "kind");
	}

}
