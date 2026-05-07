
package acme.features.auditor.project;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.Auditor;

@Service
public class AuditorProjectListService extends AbstractService<Auditor, Project> {

	//Internal state
	@Autowired
	private AuditorProjectRepository	repository;
	private Collection<Project>			projects;


	//AbstractService interface
	@Override
	public void load() {
		this.projects = this.repository.findPublishedProjects();
	}

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.projects, "title", "keyWords", "description", "kickOff", "closeOut");
	}
}
