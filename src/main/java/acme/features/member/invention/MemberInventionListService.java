
package acme.features.member.invention;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.entities.projects.Project;
import acme.features.member.project.MemberProjectRepository;
import acme.realms.Inventor;
import acme.realms.Member;

@Service
public class MemberInventionListService extends AbstractService<Member, Invention> {

	@Autowired
	private MemberInventionRepository	repository;
	@Autowired
	private MemberProjectRepository		projectRepository;
	private List<Invention>				inventions;
	private Project						project;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("projectId", int.class);
		this.project = this.projectRepository.findProjectById(id);
		if (this.project != null)
			this.inventions = this.repository.findInventionsByProjectId(this.project.getId());

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
		super.unbindObjects(this.inventions, "ticker", "name", "startMoment", "endMoment", "draftMode");
		super.unbindGlobal("projectId", this.project.getId());
		super.unbindGlobal("draftMode", this.project.getDraftMode());
		boolean isInventor = super.getRequest().getPrincipal().getRealms().stream().anyMatch(Inventor.class::isInstance);
		if (super.getRequest().hasData("projectId")) {
			super.unbindGlobal("isInventor", isInventor);
			super.unbindGlobal("projectId", this.project.getId());
		}
	}

}
