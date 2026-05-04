
package acme.features.member.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;
import acme.realms.Member;

@Service
public class MemberStrategyListService extends AbstractService<Member, Strategy> {

	@Autowired
	private MemberStrategyRepository	repository;

	private List<Strategy>				strategies;
	private int							projectId;


	@Override
	public void load() {
		this.projectId = super.getRequest().getData("projectId", int.class);
		this.strategies = this.repository.findStrategiesByProjectId(this.projectId);
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
		super.unbindObjects(this.strategies, "ticker", "name", "startMoment", "endMoment", "draftMode");
	}

}
