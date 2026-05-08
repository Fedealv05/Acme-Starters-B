
package acme.features.sponsor.sponsorship_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.sponsorships.Sponsorship;
import acme.forms.SponsorshipProject;
import acme.realms.Sponsor;

@Service
public class SponsorshipProjectUnassignService extends AbstractService<Sponsor, SponsorshipProject> {

	@Autowired
	private SponsorshipProjectRepository	repository;

	private SponsorshipProject				sponsorshipProjectForm;


	@Override
	public void load() {
		// Capturamos el ID del patrocinio que vamos a actualizar
		Integer sponsorshipId = super.getRequest().getData("id", Integer.class);

		this.sponsorshipProjectForm = super.newObject(SponsorshipProject.class);
		if (sponsorshipId != null)
			this.sponsorshipProjectForm.setSponsorshipId(sponsorshipId);
	}

	@Override
	public void authorise() {
		Sponsorship sponsorship = this.repository.findSponsorshipById(this.sponsorshipProjectForm.getSponsorshipId());
		boolean status = sponsorship != null;
		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		// No bind needed - the sponsorshipId comes from the query parameter in load()
	}

	@Override
	public void validate() {
		// Validate that we have the required sponsorship ID
		boolean hasSponsorship = this.sponsorshipProjectForm.getSponsorshipId() != 0 && 
									this.sponsorshipProjectForm.getSponsorshipId() != null;
		super.state(hasSponsorship, "sponsorshipId", "sponsor.sponsorship-assignment.error.sponsorshipId.required");
	}

	@Override
	public void execute() {
		// 1. Buscamos el patrocinio en la base de datos
		Sponsorship sponsorship = this.repository.findSponsorshipById(this.sponsorshipProjectForm.getSponsorshipId());

		if (sponsorship != null) {
			// 2. EL ENFOQUE UPDATE: Ponemos el proyecto a null
			sponsorship.setProject(null);

			// 3. Guardamos (Spring Data hará un UPDATE automático en la BBDD)
			this.repository.save(sponsorship);
		}
	}

	@Override
	public void unbind() {
		super.unbindObject(this.sponsorshipProjectForm);
		super.unbindGlobal("sponsorshipId", this.sponsorshipProjectForm.getSponsorshipId());
	}
}
