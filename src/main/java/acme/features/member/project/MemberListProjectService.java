
package acme.features.member.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.Member;

@Service
public class MemberListProjectService extends AbstractService<Member, Project> {

	@Autowired
	private MemberProjectRepository	repository;

	private List<Project>			projects;


	@Override
	public void load() {
		int memberId;

		memberId = super.getRequest().getPrincipal().getActiveRealm().getId();

		this.projects = this.repository.findProjectsByMemberId(memberId);
	}

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.projects, "title", "keyWords", "kickOff", "closeOut", "draftMode");
	}

}
