
package acme.features.auditor.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.realms.Auditor;

@Service
public class AuditorCampaignShowService extends AbstractService<Auditor, Campaign> {

	@Autowired
	private AuditorCampaignRepository	repository;

	private Campaign					campaign;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.campaign = (Campaign) this.repository.findById(id).get();
	}

	@Override
	public void authorise() {
		boolean status = this.campaign != null;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.campaign, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "monthsActive", "effort", "draftMode");
	}
}
