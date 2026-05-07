
package acme.features.member.strategy.tactic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategy.Tactic;
import acme.realms.Member;

@Service
public class MemberStrategyTacticShowService extends AbstractService<Member, Tactic> {

	@Autowired
	private MemberStrategyTacticRepository	repository;

	private Tactic							tactic;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.tactic = this.repository.findTacticById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int memberId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = this.tactic != null && this.tactic.getStrategy().getProject() != null && this.repository.findProjectMember(this.tactic.getStrategy().getProject().getId(), memberId) != null;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.tactic, "name", "notes", "expectedPercentage", "kind");
	}
}
