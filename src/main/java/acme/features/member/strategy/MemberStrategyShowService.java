
package acme.features.member.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;
import acme.realms.Member;

@Service
public class MemberStrategyShowService extends AbstractService<Member, Strategy> {

	@Autowired
	private MemberStrategyRepository	repository;

	private Strategy					strategy;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.strategy = this.repository.findStrategyById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int memberId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = this.strategy != null && this.strategy.getProject() != null && this.repository.findProjectMember(this.strategy.getProject().getId(), memberId) != null;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode");
		super.unbindGlobal("fundraiserName", this.strategy.getFundraiser().getUserAccount().getIdentity().getFullName());
		super.unbindGlobal("id", this.strategy.getId());
		super.unbindGlobal("fundraiserId", this.strategy.getFundraiser().getId());
		if (this.strategy.getProject() != null && this.strategy.getFundraiser().getUserAccount().getId() == super.getRequest().getPrincipal().getAccountId())
			super.unbindGlobal("projectId", this.strategy.getProject().getId());
	}

}
