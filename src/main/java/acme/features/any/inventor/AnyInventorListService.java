
package acme.features.any.inventor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.features.any.project.AnyProjectRepository;
import acme.realms.Inventor;

@Service
public class AnyInventorListService extends AbstractService<Any, Inventor> {

	@Autowired
	private AnyInventorRepository	repository;

	@Autowired
	private AnyProjectRepository	projectRepository;

	private List<Inventor>			inventors;

	private Project					project;


	@Override
	public void load() {

		int id = this.getRequest().getData("projectId", int.class);

		this.project = this.projectRepository.findProjectById(id);

		this.inventors = this.repository.findAssignedInventors(id);
	}

	@Override
	public void authorise() {
		boolean status;
		status = this.inventors != null && !this.project.getDraftMode();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.inventors, "userAccount.username", "keyWords", "licensed");
	}

}
