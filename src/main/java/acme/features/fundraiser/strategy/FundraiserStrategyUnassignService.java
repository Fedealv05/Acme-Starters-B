
package acme.features.fundraiser.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;
import acme.realms.Fundraiser;

@Service
public class FundraiserStrategyUnassignService extends AbstractService<Fundraiser, Strategy> {

	@Autowired
	private FundraiserStrategyRepository	repository;
	private Strategy						strategy;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		this.strategy = this.repository.findStrategyById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int fundraiserId, strategyId;
		Strategy strategy1;
		fundraiserId = super.getRequest().getPrincipal().getActiveRealm().getId();
		strategyId = super.getRequest().getData("id", int.class);
		strategy1 = this.repository.findStrategyById(strategyId);
		status = strategy1 != null && strategy1.getFundraiser().getId() == fundraiserId;

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.strategy);
	}

	@Override
	public void validate() {
		super.validateObject(this.strategy);
	}

	@Override
	public void execute() {
		this.strategy.setProject(null);
		this.repository.save(this.strategy);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode");

	}

}
