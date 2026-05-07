
package acme.features.manager.tactic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.strategy.Strategy;
import acme.entities.strategy.Tactic;
import acme.features.fundraiser.strategy.FundraiserStrategyRepository;
import acme.realms.Manager;

@Service
public class ManagerTacticListService extends AbstractService<Manager, Tactic> {

	@Autowired
	private ManagerTacticRepository			repository;

	@Autowired
	private FundraiserStrategyRepository	strategyRepository;

	private List<Tactic>					tactics;

	private Strategy						strategy;

	private Project							project;


	@Override
	public void load() {

		int id = this.getRequest().getData("strategyId", int.class);

		this.tactics = this.repository.findByStrategyId(id);
		this.strategy = this.strategyRepository.findStrategyById(id);
		if (this.strategy != null)
			this.project = this.strategy.getProject();
	}

	@Override
	public void authorise() {
		boolean status = this.strategy != null && this.project != null && this.project.getManager().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.tactics, "name", "notes", "kind");
		super.unbindGlobal("draftMode", this.strategy.getDraftMode());
		super.unbindGlobal("strategyId", this.strategy.getId());

	}

}
