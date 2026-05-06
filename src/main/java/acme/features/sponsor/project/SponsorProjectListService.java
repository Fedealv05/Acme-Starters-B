
package acme.features.sponsor.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.Sponsor;

@Service
public class SponsorProjectListService extends AbstractService<Sponsor, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorProjectRepository	repository;

	private List<Project>				projects;


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
		super.unbindObjects(this.projects, "title", "keyWords", "description", "kickOff", "closeOut", "draftMode");
	}

}
