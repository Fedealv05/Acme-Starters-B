
package acme.features.sponsor.campaign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.realms.Sponsor;

@Service
public class SponsorCampaignListService extends AbstractService<Sponsor, Campaign> {

	@Autowired
	private SponsorCampaignRepository	repository;

	private List<Campaign>				campaigns;


	@Override
	public void load() {
		int projectId = super.getRequest().getData("projectId", int.class);
		this.campaigns = this.repository.findCampaignsByProjectId(projectId);
	}

	@Override
	public void authorise() {
		boolean status = this.campaigns != null && !this.campaigns.isEmpty();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.campaigns, "ticker", "name", "startMoment", "endMoment", "draftMode");
	}
}
