
package acme.features.sponsor.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.realms.Sponsor;

@Service
public class SponsorProjectShowService extends AbstractService<Sponsor, Project> {

	@Autowired
	private SponsorProjectRepository	repository;

	private Project						project;


	@Override
	public void load() {

		int id = super.getRequest().getData("id", int.class);
		this.project = this.repository.findOneProjectById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.project != null && !this.project.getDraftMode();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {

		super.unbindObject(this.project, "title", "keyWords", "description", "kickOff", "closeOut", "effort");
	}
}
