
package acme.features.spokesperson.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.realms.Spokesperson;

@Service
public class SpokespersonCampaignUnassignService extends AbstractService<Spokesperson, Campaign> {

	@Autowired
	private SpokespersonCampaignRepository	repository;
	private Campaign						campaign;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		this.campaign = this.repository.findCampaignById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int spokespersonId, campaignId;
		Campaign campaign1;
		spokespersonId = super.getRequest().getPrincipal().getActiveRealm().getId();
		campaignId = super.getRequest().getData("id", int.class);
		campaign1 = this.repository.findCampaignById(campaignId);
		status = campaign1 != null && campaign1.getSpokesperson().getId() == spokespersonId;

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.campaign);
	}

	@Override
	public void validate() {
		super.validateObject(this.campaign);
	}

	@Override
	public void execute() {
		this.campaign.setProject(null);
		this.repository.save(this.campaign);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.campaign, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode");

	}

}
