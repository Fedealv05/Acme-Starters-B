
package acme.features.manager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.Manager;

@Service
public class ManagerProjectShowService extends AbstractService<Manager, Project> {

	@Autowired
	private ManagerProjectRepository	repository;

	private Project						project;


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.project = this.repository.findProjectById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.project != null && this.project.getManager().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.project, "title", "keyWords", "description", "kickOff", "closeOut", "draftMode", "effort");
		super.unbindGlobal("managerId", this.project.getManager().getId());
	}

}
