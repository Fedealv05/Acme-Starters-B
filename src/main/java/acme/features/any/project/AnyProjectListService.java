
package acme.features.any.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;

@Service
public class AnyProjectListService extends AbstractService<Any, Project> {

	@Autowired
	private AnyProjectRepository	repository;

	private List<Project>			projects;


	@Override
	public void load() {

		this.projects = this.repository.findByDraftModeFalse();
	}

	@Override
	public void authorise() {
		boolean status = true;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.projects, "title", "keyWords", "description", "kickOff", "closeOut");
	}

}
