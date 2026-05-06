
package acme.features.member.strategy.tactic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategy.Tactic;
import acme.realms.Member;

@Service
public class MemberStrategyTacticListService extends AbstractService<Member, Tactic> {

	@Autowired
	private MemberStrategyTacticRepository	repository;

	private List<Tactic>					tactics;
	private int								strategyId;
	private int								projectId;


	@Override
	public void load() {
		this.strategyId = super.getRequest().getData("strategyId", int.class);
		this.tactics = this.repository.findByStrategyId(this.strategyId);
		this.projectId = this.repository.findStrategyById(this.strategyId).getProject().getId();
	}

	@Override
	public void authorise() {
		boolean status;
		int memberId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = this.repository.findProjectMember(this.projectId, memberId) != null;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.tactics, "name", "expectedPercentage", "kind");
	}

}
