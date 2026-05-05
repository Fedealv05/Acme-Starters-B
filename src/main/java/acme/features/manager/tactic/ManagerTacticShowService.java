
package acme.features.manager.tactic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.datatypes.TacticKind;
import acme.entities.strategy.Tactic;
import acme.realms.Manager;

@Service
public class ManagerTacticShowService extends AbstractService<Manager, Tactic> {

	@Autowired
	private ManagerTacticRepository	repository;

	private Tactic					tactic;


	@Override
	public void load() {

		int id;
		id = super.getRequest().getData("id", int.class);
		this.tactic = this.repository.findTacticById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		status = this.tactic != null && this.tactic.getStrategy().getProject().getManager().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.tactic, "name", "notes", "expectedPercentage", "kind");
		super.unbindGlobal("draftMode", this.tactic.getStrategy().getDraftMode());
		super.unbindGlobal("strategyId", this.tactic.getStrategy().getId());
		SelectChoices kinds = SelectChoices.from(TacticKind.class, this.tactic.getKind());
		super.unbindGlobal("kinds", kinds);

	}

}
