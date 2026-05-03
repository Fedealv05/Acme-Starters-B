
package acme.features.manager.invention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.realms.Manager;

@Service
public class ManagerInventionShowService extends AbstractService<Manager, Invention> {

	@Autowired
	private ManagerInventionRepository	repository;

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
		status = this.invention != null && this.invention.getProject().getManager().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.invention, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "cost", "monthsActive", "draftMode");
		super.unbindGlobal("projectId", this.invention.getProject().getId());
	}

}
