
package acme.features.member.campaign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.entities.projects.Project;
import acme.features.member.project.MemberProjectRepository;
import acme.realms.Member;
import acme.realms.Spokesperson;

@Service
public class MemberCampaignListService extends AbstractService<Member, Campaign> {

	@Autowired
	private MemberCampaignRepository	repository;

	@Autowired
	private MemberProjectRepository		projectRepository;
	private List<Campaign>				campaigns;
	private Project						project;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("projectId", int.class);
		this.project = this.projectRepository.findProjectById(id);
		if (this.project != null)
			this.campaigns = this.repository.findCampaignsByProjectId(this.project.getId());

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
		super.unbindObjects(this.campaigns, "ticker", "name", "startMoment", "endMoment", "draftMode");
		super.unbindGlobal("draftMode", this.project.getDraftMode());
		boolean isSpokesperson = super.getRequest().getPrincipal().getRealms().stream().anyMatch(Spokesperson.class::isInstance);
		if (super.getRequest().hasData("projectId")) {
			super.unbindGlobal("isSpokesperson", isSpokesperson);
			super.unbindGlobal("projectId", this.project.getId());
		}

	}

}
