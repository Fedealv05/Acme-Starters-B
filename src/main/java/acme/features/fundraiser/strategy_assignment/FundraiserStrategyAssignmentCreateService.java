
package acme.features.fundraiser.strategy_assignment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.strategy.Strategy;
import acme.forms.StrategyAssignment;
import acme.realms.Fundraiser;

public class FundraiserStrategyAssignmentCreateService extends AbstractService<Fundraiser, StrategyAssignment> {

	@Autowired
	private FundraiserStrategyAssignmentRepository	repository;

	private StrategyAssignment						strategyAssignment;
	private List<Strategy>							strategies;
	private Project									project;


	@Override
	public void load() {
		int projectId = super.getRequest().getData("projectId", int.class);
		this.project = this.repository.findProjectById(projectId);
		int fundraiserId = super.getRequest().getPrincipal().getActiveRealm().getId();
		this.strategies = this.repository.findAvailableStrategiesByFundraiserId(fundraiserId);
		this.strategyAssignment = super.newObject(StrategyAssignment.class);
		this.strategyAssignment.setProjectId(projectId);
	}

	@Override
	public void authorise() {
		boolean status = false;

		int accountId = super.getRequest().getPrincipal().getAccountId();

		if (this.project != null) {

			Integer count = this.repository.checkProjectBelongsToAccountId(this.project.getId(), accountId);

			status = count != null && count > 0;
		}

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		int projectId = super.getRequest().getData("projectId", int.class);
		super.bindObject(this.strategyAssignment, "strategyId");
		this.strategyAssignment.setProjectId(projectId);
	}

	@Override
	public void validate() {
		super.validateObject(this.strategyAssignment);
	}

	@Override
	public void execute() {
		Strategy strategy = this.repository.findStrategyById(this.strategyAssignment.getStrategyId());
		if (strategy != null) {
			strategy.setProject(this.project);
			this.repository.save(strategy);
		}
	}

	@Override
	public void unbind() {
		SelectChoices choices;
		choices = SelectChoices.from(this.strategies, "ticker", null);
		super.unbindObject(this.strategyAssignment, "strategyId");
		super.unbindGlobal("listaStrategies", choices);
		super.unbindGlobal("projectId", this.project.getId());
	}

}
