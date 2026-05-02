
package acme.features.manager.invention;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.entities.projects.Project;
import acme.features.manager.project.ManagerProjectRepository;
import acme.realms.Manager;

@Service
public class ManagerInventionListService extends AbstractService<Manager, Invention> {

	@Autowired
	private ManagerInventionRepository	repository;

	@Autowired
	private ManagerProjectRepository	projectRepository;

	private List<Invention>				inventions;

	private Project						project;


	@Override
	public void load() {
		int id = this.getRequest().getData("projectId", int.class);

		this.inventions = this.repository.findByProjectId(id);
		this.project = this.projectRepository.findProjectById(id);
	}

	@Override
	public void authorise() {
		boolean status = this.project != null && this.project.getManager().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.inventions, "ticker", "name", "startMoment", "endMoment", "draftMode");
		super.unbindGlobal("projectId", this.project.getId());
	}

}
