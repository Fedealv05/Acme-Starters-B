
package acme.features.sponsor.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;
import acme.realms.Sponsor;

@Service
public class SponsorStrategyListService extends AbstractService<Sponsor, Strategy> {

	@Autowired
	private SponsorStrategyRepository	repository;

	private List<Strategy>				strategies;


	@Override
	public void load() {
		int projectId = super.getRequest().getData("projectId", int.class);
		this.strategies = this.repository.findStrategiesByProjectId(projectId);
	}

	@Override
	public void authorise() {
		boolean status = this.strategies != null && !this.strategies.isEmpty();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.strategies, "ticker", "name", "startMoment", "endMoment", "draftMode");
	}
}
