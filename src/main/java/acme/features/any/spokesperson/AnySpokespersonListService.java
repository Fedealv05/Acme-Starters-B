
package acme.features.any.spokesperson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.features.any.project.AnyProjectRepository;
import acme.realms.Spokesperson;

@Service
public class AnySpokespersonListService extends AbstractService<Any, Spokesperson> {

	@Autowired
	private AnySpokespersonRepository	repository;

	@Autowired
	private AnyProjectRepository		projectRepository;

	private List<Spokesperson>			spokespersons;

	private Project						project;


	@Override
	public void load() {

		int id = this.getRequest().getData("projectId", int.class);

		this.project = this.projectRepository.findProjectById(id);

		this.spokespersons = this.repository.findAssignedSpokespersons(id);
	}

	@Override
	public void authorise() {
		boolean status;
		status = this.spokespersons != null && !this.project.getDraftMode();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.spokespersons, "userAccount.username", "cv", "licensed");
	}

}
