
package acme.features.any.sponsorship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.sponsorships.Sponsorship;

@Service
public class AnySponsorshipShowService extends AbstractService<Any, Sponsorship> {

	@Autowired
	private AnySponsorshipRepository	repository;

	private Sponsorship					sponsorship;


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.sponsorship = this.repository.findSponsorshipById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		status = this.sponsorship != null && !this.sponsorship.getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.sponsorship, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "monthsActive", "totalMoney");
		super.unbindGlobal("id", this.sponsorship.getId());
		super.unbindGlobal("sponsorId", this.sponsorship.getSponsor().getId());
		if (this.sponsorship.getProject() != null) {
			super.unbindGlobal("title", this.sponsorship.getProject().getTitle());
			if (this.sponsorship.getSponsor().getUserAccount().getId() == super.getRequest().getPrincipal().getAccountId()) {
				super.unbindGlobal("projectId", this.sponsorship.getProject().getId());
				if (MomentHelper.getCurrentMoment().before(this.sponsorship.getProjectUnassignMoment()))
					super.unbindGlobal("projectUnasssignMoment", true);
			}
		}

	}

}
