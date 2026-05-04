
package acme.features.member.invention;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.realms.Member;

@Service
public class MemberInventionListService extends AbstractService<Member, Invention> {

	@Autowired
	private MemberInventionRepository	repository;

	private List<Invention>				inventions;
	private int							projectId;


	@Override
	public void load() {
		this.projectId = super.getRequest().getData("projectId", int.class);

		this.inventions = this.repository.findInventionsByProjectId(this.projectId);
	}

	@Override
	public void authorise() {
		boolean status;
		int memberId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = this.repository.findProjectMember(this.projectId, memberId) != null;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.inventions, "ticker", "name", "startMoment", "endMoment", "draftMode");
	}

}
