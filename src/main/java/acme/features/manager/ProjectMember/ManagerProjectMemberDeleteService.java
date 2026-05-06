
package acme.features.manager.ProjectMember;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.UserAccount;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.projects.ProjectMember;
import acme.features.manager.project.ManagerProjectRepository;
import acme.realms.Fundraiser;
import acme.realms.Inventor;
import acme.realms.Manager;
import acme.realms.Member;
import acme.realms.Spokesperson;

@Service
public class ManagerProjectMemberDeleteService extends AbstractService<Manager, ProjectMember> {

	@Autowired
	private ManagerProjectMemberRepository	repository;

	@Autowired
	private ManagerProjectRepository		projectRepository;

	@Autowired
	private ManagerFundraiserRepository		fundraiserRepository;

	@Autowired
	private ManagerInventorRepository		inventorRepository;

	@Autowired
	private ManagerSpokespersonRepository	spokespersonRepository;

	private ProjectMember					projectMember;

	private Member							member;

	private Project							project;


	@Override
	public void load() {

		int id;
		id = super.getRequest().getData("projectId", int.class);
		this.project = this.projectRepository.findProjectById(id);
		this.projectMember = super.newObject(ProjectMember.class);
		this.projectMember.setProject(this.project);

	}

	@Override
	public void authorise() {
		String roleName = super.getRequest().getData("role", String.class);
		boolean validRole = "INVENTOR".equals(roleName) || "FUNDRAISER".equals(roleName) || "SPOKESPERSON".equals(roleName);
		Boolean status = this.project != null && this.project.getManager().getId() == super.getRequest().getPrincipal().getActiveRealm().getId() && validRole && this.project.getDraftMode();
		super.setAuthorised(status);

	}

	@Override
	public void bind() {
		super.bindObject(this.projectMember);

		int selectedRoleId = super.getRequest().getData("selectedUser", int.class);

		String roleName = super.getRequest().getData("role", String.class);
		UserAccount userAccount = null;

		if ("INVENTOR".equals(roleName)) {
			Inventor inventor = this.inventorRepository.findInventorById(selectedRoleId);
			if (inventor != null)
				userAccount = inventor.getUserAccount();
		} else if ("FUNDRAISER".equals(roleName)) {
			Fundraiser fundraiser = this.fundraiserRepository.findFundraiserById(selectedRoleId);
			if (fundraiser != null)
				userAccount = fundraiser.getUserAccount();
		} else if ("SPOKESPERSON".equals(roleName)) {
			Spokesperson spokesperson = this.spokespersonRepository.findSpokespersonById(selectedRoleId);
			if (spokesperson != null)
				userAccount = spokesperson.getUserAccount();
		}

		if (userAccount != null) {
			Member member = this.repository.findMemberByUserAccountId(userAccount.getId());
			if (member != null) {
				ProjectMember existingAssignment = this.repository.findProjectMemberByProjectIdAndMemberId(this.project.getId(), member.getId());

				if (existingAssignment != null)
					this.projectMember = existingAssignment;
				else
					this.projectMember.setMember(null);
			}
		}
	}

	@Override
	public void validate() {
		super.validateObject(this.projectMember);

		if (this.projectMember.getId() == 0 || this.projectMember.getMember() == null)
			super.state(false, "selectedUser", "projectMember.delete.validation.missingUser");

	}

	@Override
	public void execute() {
		this.repository.delete(this.projectMember);
	}

	@Override
	public void unbind() {

		super.unbindObject(this.projectMember);

		SelectChoices userChoices = null;
		int projectId = super.getRequest().getData("projectId", int.class);
		String roleName = super.getRequest().getData("role", String.class);

		if ("INVENTOR".equals(roleName)) {
			List<Inventor> inventores = this.repository.findAssignedInventors(projectId);
			userChoices = SelectChoices.from(inventores, "userAccount.username", null);
		} else if ("FUNDRAISER".equals(roleName)) {
			List<Fundraiser> fundraisers = this.repository.findAssignedFundraisers(projectId);
			userChoices = SelectChoices.from(fundraisers, "userAccount.username", null);
		} else if ("SPOKESPERSON".equals(roleName)) {
			List<Spokesperson> spokespersons = this.repository.findAssignedSpokesperson(projectId);
			userChoices = SelectChoices.from(spokespersons, "userAccount.username", null);
		}

		super.unbindGlobal("userChoices", userChoices);
		super.unbindGlobal("projectId", projectId);
		super.unbindGlobal("role", roleName);
	}
}
