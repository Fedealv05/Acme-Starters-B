
package acme.features.inventor.invention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.realms.Inventor;

@Service
public class InventorInventionUnassignService extends AbstractService<Inventor, Invention> {

	@Autowired
	private InventorInventionRepository	repository;
	private Invention					invention;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		this.invention = this.repository.findInventionById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int inventorId, inventionId;
		Invention invention1;
		inventorId = super.getRequest().getPrincipal().getActiveRealm().getId();
		inventionId = super.getRequest().getData("id", int.class);
		invention1 = this.repository.findInventionById(inventionId);
		status = invention1 != null && invention1.getInventor().getId() == inventorId;

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.invention);
	}

	@Override
	public void validate() {
		super.validateObject(this.invention);
	}

	@Override
	public void execute() {
		this.invention.setProject(null);
		this.repository.save(this.invention);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.invention, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode");

	}

}
