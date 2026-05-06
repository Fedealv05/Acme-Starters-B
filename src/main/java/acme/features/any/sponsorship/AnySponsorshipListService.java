
package acme.features.any.sponsorship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.sponsorships.Sponsorship;

@Service
public class AnySponsorshipListService extends AbstractService<Any, Sponsorship> {

	@Autowired
	private AnySponsorshipRepository	repository;

	private Collection<Sponsorship>		sponsorships;


	@Override
	public void load() {
		if (super.getRequest().hasData("projectId")) {
			int projectId = super.getRequest().getData("projectId", int.class);
			this.sponsorships = this.repository.findByProjectId(projectId);
		} else
			this.sponsorships = this.repository.findByDraftModeFalse();
	}

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.sponsorships, "ticker", "name", "startMoment", "endMoment");

		int projectId = super.getRequest().getData("projectId", int.class);

		super.unbindGlobal("projectId", projectId);
	}

}
