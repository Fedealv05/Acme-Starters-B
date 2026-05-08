
package acme.features.manager.inventor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.features.manager.project.ManagerProjectRepository;
import acme.features.manager.project_member.ManagerProjectMemberRepository;
import acme.realms.Inventor;
import acme.realms.Manager;

public class ManagerInventorListService extends AbstractService<Manager, Inventor> {

	@Autowired
	private ManagerProjectMemberRepository	repository;

	@Autowired
	private ManagerProjectRepository		projectRepository;

	private Project							project;

	private List<Inventor>					inventors;


	@Override
	public void load() {

		int id;
		id = this.getRequest().getData("projectId", int.class);

		this.inventors = this.repository.findAssignedInventors(id);

		this.project = this.projectRepository.findProjectById(id);
	}

	@Override

	public void authorise() {
		boolean status = this.project != null && this.project.getManager().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.inventors, "userAccount.username", "keyWords", "licensed");
		super.unbindGlobal("draftMode", this.project.getDraftMode());
		super.unbindGlobal("projectId", this.project.getId());

	}

}
