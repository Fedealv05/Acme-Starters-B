
package acme.features.member.fundraiser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.realms.Fundraiser;
import acme.realms.Member;

@Service
public class MemberFundraiserShowService extends AbstractService<Member, Fundraiser> {

	@Autowired
	private MemberFundraiserRepository	repository;

	private Fundraiser					fundraiser;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
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
