
package acme.features.auditor.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;
import acme.realms.Auditor;

@Service
public class AuditorStrategyShowService extends AbstractService<Auditor, Strategy> {

	@Autowired
	private AuditorStrategyRepository	repository;

	private Strategy					strategy;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.strategy = (Strategy) this.repository.findById(id).orElse(null);
	}

	@Override
	public void authorise() {
		boolean status = this.strategy != null;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "monthsActive", "expectedPercentage", "draftMode");
		super.unbindGlobal("projectId", this.strategy.getProject().getId());
	}
}
