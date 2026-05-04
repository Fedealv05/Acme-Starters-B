
package acme.features.member.invention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.realms.Member;

@Service
public class MemberInventionShowService extends AbstractService<Member, Invention> {

	@Autowired
	private MemberInventionRepository	repository;

	private Invention					invention;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.invention = this.repository.findInventionById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int memberId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = this.invention != null && this.invention.getProject() != null && this.repository.findProjectMember(this.invention.getProject().getId(), memberId) != null;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.invention, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode");
		super.unbindGlobal("inventorName", this.invention.getInventor().getUserAccount().getIdentity().getFullName());
	}

}
