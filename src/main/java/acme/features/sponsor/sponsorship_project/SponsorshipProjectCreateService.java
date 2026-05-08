
package acme.features.sponsor.sponsorship_project;

import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.sponsorships.Sponsorship;
import acme.forms.SponsorshipProject;
import acme.realms.Sponsor;

@Service

public class SponsorshipProjectCreateService extends AbstractService<Sponsor, SponsorshipProject> {

	@Autowired
	private SponsorshipProjectRepository	repository;
	private SponsorshipProject				sponsorshipProject;
	private Collection<Sponsorship>			sponsorships;
	private Project							project;


	@Override
	public void load() {
		Integer projectId = super.getRequest().getData("projectId", int.class);
		this.project = this.repository.findProjectById(projectId);
		int sponsorId = super.getRequest().getPrincipal().getActiveRealm().getId();
		this.sponsorships = this.repository.findAvailableSponsorshipsBySponsorId(sponsorId);
		this.sponsorshipProject = super.newObject(SponsorshipProject.class);
		this.sponsorshipProject.setProjectId(projectId);

	}

	@Override
	public void authorise() {
		boolean status;
		status = this.project != null && !this.project.getDraftMode();
		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		int projectId = super.getRequest().getData("projectId", int.class);
		super.bindObject(this.sponsorshipProject, "sponsorshipId");
		this.sponsorshipProject.setProjectId(projectId);
	}

	@Override
	public void validate() {
		super.validateObject(this.sponsorshipProject);
		boolean hasSponsorship = this.sponsorshipProject.getSponsorshipId() != 0;
		super.state(hasSponsorship, "sponsorshipId", "sponsor.sponsorship-assignment.error.sponsorshipId.required");
	}

	@Override
	public void execute() {
		Sponsorship sponsorship = this.repository.findSponsorshipById(this.sponsorshipProject.getSponsorshipId());
		if (sponsorship != null) {
			Date projectUnassignMoment = MomentHelper.deltaFromCurrentMoment(24, ChronoUnit.HOURS);
			sponsorship.setProjectUnassignMoment(projectUnassignMoment);
			sponsorship.setProject(this.project);
			this.repository.save(sponsorship);
		}
	}

	@Override
	public void unbind() {
		SelectChoices choices;
		choices = SelectChoices.from(this.sponsorships, "ticker", null);
		super.unbindObject(this.sponsorshipProject, "sponsorshipId");
		super.unbindGlobal("listaSponsorships", choices);
		super.unbindGlobal("projectId", this.project.getId());
	}
}
