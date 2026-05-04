
package acme.features.manager.campaign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.entities.projects.Project;
import acme.features.manager.project.ManagerProjectRepository;
import acme.realms.Manager;

@Service
public class ManagerCampaignListService extends AbstractService<Manager, Campaign> {

	@Autowired
	private ManagerCampaignRepository	repository;

	@Autowired
	private ManagerProjectRepository	projectRepository;

	private List<Campaign>				campaigns;

	private Project						project;


	@Override
	public void load() {
		int id = this.getRequest().getData("projectId", int.class);

		this.campaigns = this.repository.findByProjectId(id);
		this.project = this.projectRepository.findProjectById(id);
	}

	@Override
	public void authorise() {
		boolean status = this.project != null && this.project.getManager().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.campaigns, "ticker", "name", "startMoment", "endMoment", "draftMode");
		super.unbindGlobal("projectId", this.project.getId());
	}

}
