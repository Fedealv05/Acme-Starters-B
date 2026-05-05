
package acme.features.manager.fundraiser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.features.manager.ProjectMember.ManagerFundraiserRepository;
import acme.realms.Fundraiser;
import acme.realms.Manager;

@Service
public class ManagerFundraiserShowService extends AbstractService<Manager, Fundraiser> {

	@Autowired
	private ManagerFundraiserRepository	repository;

	private Fundraiser					fundraiser;


	@Override
	public void load() {

		int id;
		id = super.getRequest().getData("id", int.class);
		this.fundraiser = this.repository.findFundraiserById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		status = this.fundraiser != null;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.fundraiser, "userAccount.username", "bank", "statement", "agent");
	}

}
