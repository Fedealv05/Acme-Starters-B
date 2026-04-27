
package acme.features.spokesperson.milestone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.datatypes.MilestoneKind;
import acme.entities.campaigns.Milestone;
import acme.realms.Spokesperson;

@Service
public class SpokespersonMilestoneShowService extends AbstractService<Spokesperson, Milestone> {

	@Autowired
	private SpokespersonMilestoneRepository	repository;

	private Milestone						milestone;


	@Override
	public void load() {

		int id;
		id = super.getRequest().getData("id", int.class);
		this.milestone = this.repository.findMilestoneById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		status = this.milestone != null && this.milestone.getCampaign().getSpokesperson().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.milestone, "title", "achievements", "effort", "kind");
		super.unbindGlobal("draftMode", this.milestone.getCampaign().getDraftMode());
		super.unbindGlobal("campaignId", this.milestone.getCampaign().getId());
		SelectChoices kinds = SelectChoices.from(MilestoneKind.class, this.milestone.getKind());
		super.unbindGlobal("kinds", kinds);
	}

}
