
package acme.features.member.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.Member;

@Service
public class MemberProjectShowService extends AbstractService<Member, Project> {

	@Autowired
	private MemberProjectRepository	repository;

	private Project					project;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		this.project = this.repository.findProjectById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int memberId;

		memberId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = this.project != null && this.repository.findProjectMember(this.project.getId(), memberId) != null;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.project, "title", "keyWords", "description", "kickOff", "closeOut", "draftMode", "effort");
		super.unbindGlobal("id", this.project.getId());
		super.unbindGlobal("managerId", this.project.getManager().getId());
		super.unbindGlobal("draftMode", this.project.getDraftMode());
	}

}
