
package acme.features.auditor.invention;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.realms.Auditor;

@Service
public class AuditorInventionListService extends AbstractService<Auditor, Invention> {

	@Autowired
	private AuditorInventionRepository	repository;

	private List<Invention>				inventions;


	@Override
	public void load() {
		int projectId = super.getRequest().getData("projectId", int.class);
		this.inventions = this.repository.findInventionsByProjectId(projectId);
	}

	@Override
	public void authorise() {
		boolean status = this.inventions != null && !this.inventions.isEmpty();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.inventions, "ticker", "name", "startMoment", "endMoment", "draftMode");
	}
}
