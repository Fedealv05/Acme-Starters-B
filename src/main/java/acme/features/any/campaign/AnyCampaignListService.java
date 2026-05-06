
package acme.features.any.campaign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;

@Service
public class AnyCampaignListService extends AbstractService<Any, Campaign> {

	@Autowired
	private AnyCampaignRepository	repository;

	private List<Campaign>			campaigns;


	@Override
	public void load() {

		if (super.getRequest().hasData("projectId"))
			this.campaigns = this.repository.findByProjectId(this.getRequest().getData("projectId", Integer.class));

		else
			this.campaigns = this.repository.findByDraftModeFalse();
	}

	@Override
	public void authorise() {
		boolean todosPublicados = true;
		if (super.getRequest().hasData("projectId"))
			todosPublicados = this.campaigns.stream().allMatch(inv -> inv.getProject().getDraftMode() == false);
		boolean status = this.campaigns != null && todosPublicados;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.campaigns, "ticker", "name", "startMoment", "endMoment");
	}

}
