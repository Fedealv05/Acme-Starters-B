
package acme.features.manager.inventor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import acme.client.services.AbstractService;
import acme.features.manager.project_member.ManagerInventorRepository;
import acme.realms.Inventor;
import acme.realms.Manager;

@Repository
public class ManagerInventorShowService extends AbstractService<Manager, Inventor> {

	@Autowired
	private ManagerInventorRepository	repository;

	private Inventor					inventor;


	@Override
	public void load() {

		int id;
		id = super.getRequest().getData("id", int.class);
		this.inventor = this.repository.findInventorById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		status = this.inventor != null;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.inventor, "userAccount.username", "keyWords", "bio", "licensed");
	}

}
