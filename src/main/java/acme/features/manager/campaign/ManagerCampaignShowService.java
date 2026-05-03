
package acme.features.manager.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.realms.Manager;

@Service
public class ManagerCampaignShowService extends AbstractService<Manager, Campaign> {

	@Autowired
	private ManagerCampaignRepository	repository;

	private Campaign					campaign;


	@Override
	public void load() {

		int id;
		id = super.getRequest().getData("id", int.class);
		this.campaign = this.repository.findCampaignById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		status = this.campaign != null && this.campaign.getProject().getManager().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.campaign, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "monthsActive", "effort", "draftMode");
		super.unbindGlobal("projectId", this.campaign.getProject().getId());
	}

}
