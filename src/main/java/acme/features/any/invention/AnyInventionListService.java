
package acme.features.any.invention;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;

@Service
public class AnyInventionListService extends AbstractService<Any, Invention> {

	@Autowired
	private AnyInventionRepository	repository;

	private List<Invention>			inventions;


	@Override
	public void load() {

		if (super.getRequest().hasData("projectId"))
			this.inventions = this.repository.findByProjectId(this.getRequest().getData("projectId", Integer.class));

		else
			this.inventions = this.repository.findByDraftModeFalse();
	}

	@Override
	public void authorise() {
		boolean todosPublicados = true;
		if (super.getRequest().hasData("projectId"))
			todosPublicados = this.inventions.stream().allMatch(inv -> inv.getProject().getDraftMode() == false);
		boolean status = this.inventions != null && todosPublicados;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.inventions, "ticker", "name", "startMoment", "endMoment");
	}

}
