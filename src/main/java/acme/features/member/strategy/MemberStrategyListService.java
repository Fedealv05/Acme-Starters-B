
package acme.features.member.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.strategy.Strategy;
import acme.features.member.project.MemberProjectRepository;
import acme.realms.Fundraiser;
import acme.realms.Member;

@Service
public class MemberStrategyListService extends AbstractService<Member, Strategy> {

	@Autowired
	private MemberStrategyRepository	repository;
	@Autowired
	private MemberProjectRepository		projectRepository;

	private List<Strategy>				strategies;
	private Project						project;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("projectId", int.class);
		this.project = this.projectRepository.findProjectById(id);
		if (this.project != null)
			this.strategies = this.repository.findStrategiesByProjectId(this.project.getId());

	}

	@Override
	public void authorise() {
		boolean status;
		int memberId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = this.project != null && this.repository.findProjectMember(this.project.getId(), memberId) != null;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.strategies, "ticker", "name", "startMoment", "endMoment", "draftMode");
		super.unbindGlobal("draftMode", this.project.getDraftMode());
		boolean isFundraiser = super.getRequest().getPrincipal().getRealms().stream().anyMatch(Fundraiser.class::isInstance);
		if (super.getRequest().hasData("projectId")) {
			super.unbindGlobal("isFundraiser", isFundraiser);
			super.unbindGlobal("projectId", this.project.getId());
		}
	}

}
