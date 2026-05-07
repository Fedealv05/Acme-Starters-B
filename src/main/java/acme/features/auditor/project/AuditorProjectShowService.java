
package acme.features.auditor.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.Auditor;

@Service
public class AuditorProjectShowService extends AbstractService<Auditor, Project> {

	//Internal state
	@Autowired
	private AuditorProjectRepository	repository;
	private Project						project;


	//AbstractService interface
	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		this.project = this.repository.findProjectById(id);
	}

	@Override
	public void authorise() {
		boolean status = this.project != null && !this.project.getDraftMode();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.project, "title", "keyWords", "description", "kickOff", "closeOut", "effort");
		super.unbindGlobal("id", this.project.getId());
		super.unbindGlobal("managerId", this.project.getManager().getId());
	}
}
