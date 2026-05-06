
package acme.features.member.spokesperson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.realms.Member;
import acme.realms.Spokesperson;

@Service
public class MemberSpokespersonListService extends AbstractService<Member, Spokesperson> {

	@Autowired
	private MemberSpokespersonRepository	repository;

	private List<Spokesperson>				spokespersons;
	private int								projectId;


	@Override
	public void load() {
		this.projectId = super.getRequest().getData("projectId", int.class);

		this.spokespersons = this.repository.findSpokespersonsByProjectId(this.projectId);
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
		super.unbindObjects(this.spokespersons, "userAccount.username", "cv", "licensed");
	}

}
