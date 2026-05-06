
package acme.features.any.fundraiser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.features.any.project.AnyProjectRepository;
import acme.realms.Fundraiser;

@Service
public class AnyFundraiserListService extends AbstractService<Any, Fundraiser> {

	@Autowired
	private AnyFundraiserRepository	repository;

	@Autowired
	private AnyProjectRepository	projectRepository;

	private List<Fundraiser>		fundraisers;

	private Project					project;


	@Override
	public void load() {

		int id = this.getRequest().getData("projectId", int.class);

		this.project = this.projectRepository.findProjectById(id);

		this.fundraisers = this.repository.findAssignedFundraisers(id);
	}

	@Override
	public void authorise() {
		boolean status;
		status = this.fundraisers != null && !this.project.getDraftMode();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.fundraisers, "userAccount.username", "bank", "agent");
	}

}
