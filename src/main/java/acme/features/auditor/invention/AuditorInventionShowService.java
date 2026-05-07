
package acme.features.auditor.invention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.realms.Auditor;

@Service
public class AuditorInventionShowService extends AbstractService<Auditor, Invention> {

	@Autowired
	private AuditorInventionRepository	repository;

	private Invention					invention;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.invention = (Invention) this.repository.findById(id).orElse(null);
	}

	@Override
	public void authorise() {
		boolean status = this.invention != null;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.invention, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "cost", "monthsActive", "draftMode");
	}
}
