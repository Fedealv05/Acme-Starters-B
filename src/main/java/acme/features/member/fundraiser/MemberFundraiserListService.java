
package acme.features.member.fundraiser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.realms.Fundraiser;
import acme.realms.Member;

@Service
public class MemberFundraiserListService extends AbstractService<Member, Fundraiser> {

	@Autowired
	private MemberFundraiserRepository	repository;

	private List<Fundraiser>			fundraisers;
	private int							projectId;


	@Override
	public void load() {
		this.projectId = super.getRequest().getData("projectId", int.class);

		this.fundraisers = this.repository.findFundraisersByProjectId(this.projectId);
	}

	@Override
	public void authorise() {
		boolean status;
		int memberId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = this.repository.findProjectMember(this.projectId, memberId) != null;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.fundraisers, "userAccount.username", "bank", "agent");
	}
}
