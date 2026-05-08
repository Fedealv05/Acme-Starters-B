
package acme.features.any.sponsorship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.sponsorships.Sponsorship;
import acme.features.any.project.AnyProjectRepository;
import acme.realms.Sponsor;

@Service
public class AnySponsorshipListService extends AbstractService<Any, Sponsorship> {

	@Autowired
	private AnySponsorshipRepository	repository;

	private Collection<Sponsorship>		sponsorships;

	private Project						project;

	@Autowired
	private AnyProjectRepository		projectRepository;


	@Override
	public void load() {
		if (super.getRequest().hasData("projectId")) {
			Integer projectId = super.getRequest().getData("projectId", Integer.class);
			this.sponsorships = this.repository.findByProjectId(projectId);
			this.project = this.projectRepository.findProjectById(projectId);
		} else
			this.sponsorships = this.repository.findByDraftModeFalse();
	}

	@Override
	public void authorise() {
		boolean status = true;
		if (super.getRequest().hasData("projectId") && this.project == null)
			status = false;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.sponsorships, "ticker", "name", "startMoment", "endMoment");

		boolean isSponsor = super.getRequest().getPrincipal().getRealms().stream().anyMatch(Sponsor.class::isInstance);
		if (super.getRequest().hasData("projectId")) {
			super.unbindGlobal("isSponsor", isSponsor);
			super.unbindGlobal("projectId", this.project.getId());
		}
	}

}
