
package acme.features.inventor.invention_assignment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.entities.projects.Project;
import acme.forms.InventionAssignment;
import acme.realms.Inventor;

@Service
public class InventorInventionAssignmentCreateService extends AbstractService<Inventor, InventionAssignment> {

	@Autowired
	private InventorInventionAssignmentRepository	repository;

	private InventionAssignment						inventionAssigment;
	private List<Invention>							inventions;
	private Project									project;


	@Override
	public void load() {
		int projectId = super.getRequest().getData("projectId", int.class);
		this.project = this.repository.findProjectById(projectId);
		int inventorId = super.getRequest().getPrincipal().getActiveRealm().getId();
		this.inventions = this.repository.findAvailableInventionsByInventorId(inventorId);
		this.inventionAssigment = super.newObject(InventionAssignment.class);
		this.inventionAssigment.setProjectId(projectId);
	}

	@Override
	public void authorise() {
		boolean status = false;

		int accountId = super.getRequest().getPrincipal().getAccountId();

		if (this.project != null) {

			Integer count = this.repository.checkProjectBelongsToAccountId(this.project.getId(), accountId);

			status = count != null && count > 0;
		}

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		int projectId = super.getRequest().getData("projectId", int.class);
		super.bindObject(this.inventionAssigment, "inventionId");
		this.inventionAssigment.setProjectId(projectId);
	}

	@Override
	public void validate() {
		super.validateObject(this.inventionAssigment);
	}

	@Override
	public void execute() {
		Invention invention = this.repository.findInventionById(this.inventionAssigment.getInventionId());
		if (invention != null) {
			invention.setProject(this.project);
			this.repository.save(invention);
		}
	}

	@Override
	public void unbind() {
		SelectChoices choices;
		choices = SelectChoices.from(this.inventions, "ticker", null);
		super.unbindObject(this.inventionAssigment, "inventionId");
		super.unbindGlobal("listaInventions", choices);
		super.unbindGlobal("projectId", this.project.getId());
	}
}
