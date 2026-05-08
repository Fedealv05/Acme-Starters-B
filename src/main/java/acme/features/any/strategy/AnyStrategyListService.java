
package acme.features.any.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;

@Service
public class AnyStrategyListService extends AbstractService<Any, Strategy> {

	@Autowired
	private AnyStrategyRepository	repository;

	private List<Strategy>			strategies;


	@Override
	public void load() {
		if (super.getRequest().hasData("projectId"))
			this.strategies = this.repository.findByProjectId(this.getRequest().getData("projectId", Integer.class));

		else
			this.strategies = this.repository.findByDraftModeFalse();
	}

	@Override
	public void authorise() {
		boolean todosPublicados = true;
		if (super.getRequest().hasData("projectId"))
			todosPublicados = this.strategies.stream().allMatch(inv -> !inv.getProject().getDraftMode());
		boolean status = this.strategies != null && todosPublicados;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.strategies, "ticker", "name", "startMoment", "endMoment");
	}

}
