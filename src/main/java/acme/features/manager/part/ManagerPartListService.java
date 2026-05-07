
package acme.features.manager.part;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.entities.inventions.Part;
import acme.entities.projects.Project;
import acme.features.inventor.invention.InventorInventionRepository;
import acme.realms.Manager;

@Service
public class ManagerPartListService extends AbstractService<Manager, Part> {

	@Autowired
	private ManagerPartRepository		repository;

	@Autowired
	private InventorInventionRepository	inventionRepository;

	private List<Part>					parts;

	private Invention					invention;

	private Project						project;


	@Override
	public void load() {

		int id = this.getRequest().getData("inventionId", int.class);

		this.parts = this.repository.findByInventionId(id);
		this.invention = this.inventionRepository.findInventionById(id);
		if (this.invention != null)
			this.project = this.invention.getProject();
	}

	@Override
	public void authorise() {
		boolean status = this.invention != null && this.project != null && this.project.getManager().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.parts, "name", "cost", "kind");
		super.unbindGlobal("draftMode", this.invention.getDraftMode());
		super.unbindGlobal("inventionId", this.invention.getId());

	}

}
