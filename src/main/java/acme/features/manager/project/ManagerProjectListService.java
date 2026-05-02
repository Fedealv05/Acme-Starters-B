
package acme.features.manager.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.Manager;

@Service
public class ManagerProjectListService extends AbstractService<Manager, Project> {

	@Autowired
	private ManagerProjectRepository	repository;

	private List<Project>				projects;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getPrincipal().getActiveRealm().getId();

		this.projects = this.repository.findByManagerId(id);
	}

	@Override
	public void authorise() {
		boolean status = this.projects.stream().map(i -> i.getManager().getId()).allMatch(i -> i == super.getRequest().getPrincipal().getActiveRealm().getId());
		;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.projects, "title", "keyWords", "description", "kickOff", "closeOut", "draftMode");
	}

}
