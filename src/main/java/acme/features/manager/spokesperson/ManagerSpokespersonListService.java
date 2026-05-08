
package acme.features.manager.spokesperson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.features.manager.project.ManagerProjectRepository;
import acme.features.manager.project_member.ManagerProjectMemberRepository;
import acme.realms.Manager;
import acme.realms.Spokesperson;

@Service
public class ManagerSpokespersonListService extends AbstractService<Manager, Spokesperson> {

	@Autowired
	private ManagerProjectMemberRepository	repository;

	@Autowired
	private ManagerProjectRepository		projectRepository;

	private Project							project;

	private List<Spokesperson>				spokespersons;


	@Override
	public void load() {

		int id;
		id = this.getRequest().getData("projectId", int.class);

		this.spokespersons = this.repository.findAssignedSpokesperson(id);

		this.project = this.projectRepository.findProjectById(id);
	}

	@Override

	public void authorise() {
		boolean status = this.project != null && this.project.getManager().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.spokespersons, "userAccount.username", "cv", "licensed");
		super.unbindGlobal("draftMode", this.project.getDraftMode());
		super.unbindGlobal("projectId", this.project.getId());

	}

}
