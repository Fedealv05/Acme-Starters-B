
package acme.features.member.inventor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.realms.Inventor;
import acme.realms.Member;

@Service
public class MemberInventorListService extends AbstractService<Member, Inventor> {

	@Autowired
	private MemberInventorRepository	repository;

	private List<Inventor>				inventors;
	private int							projectId;


	@Override
	public void load() {
		this.projectId = super.getRequest().getData("projectId", int.class);

		this.inventors = this.repository.findInventorsByProjectId(this.projectId);
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
		super.unbindObjects(this.inventors, "userAccount.username", "bio", "licensed");
	}

}
