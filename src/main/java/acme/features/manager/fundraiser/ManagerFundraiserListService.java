
package acme.features.manager.fundraiser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.features.manager.ProjectMember.ManagerProjectMemberRepository;
import acme.features.manager.project.ManagerProjectRepository;
import acme.realms.Fundraiser;
import acme.realms.Manager;

public class ManagerFundraiserListService extends AbstractService<Manager, Fundraiser> {

	@Autowired
	private ManagerProjectMemberRepository	repository;

	@Autowired
	private ManagerProjectRepository		projectRepository;

	private Project							project;

	private List<Fundraiser>				fundraisers;


	@Override
	public void load() {

		int id;
		id = this.getRequest().getData("projectId", int.class);

		this.fundraisers = this.repository.findAssignedFundraiser(id);

		this.project = this.projectRepository.findProjectById(id);
	}

	@Override

	public void authorise() {
		boolean status = this.project != null && this.project.getManager().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.fundraisers, "userAccount.username", "bank", "agent");
		super.unbindGlobal("draftMode", this.project.getDraftMode());
		super.unbindGlobal("projectId", this.project.getId());

	}

}
