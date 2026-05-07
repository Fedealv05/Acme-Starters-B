
package acme.features.auditor.campaign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.realms.Auditor;

@Service
public class AuditorCampaignListService extends AbstractService<Auditor, Campaign> {

	@Autowired
	private AuditorCampaignRepository	repository;

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
