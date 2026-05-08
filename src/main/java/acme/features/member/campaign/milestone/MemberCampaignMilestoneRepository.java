
package acme.features.member.campaign.milestone;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaigns.Campaign;
import acme.entities.campaigns.Milestone;
import acme.entities.projects.Project;
import acme.entities.projects.ProjectMember;

@Repository
public interface MemberCampaignMilestoneRepository extends AbstractRepository {

	List<Milestone> findByCampaignId(int campaignId);

	@Query("select pm from ProjectMember pm where pm.project.id = :projectId and pm.member.id = :memberId")
	ProjectMember findProjectMember(int projectId, int memberId);

	@Query("select m from Milestone m where m.id = :id")
	Milestone findMilestoneById(int id);

	@Query("select c from Campaign c where c.id = :id")
	Campaign findCampaignById(int id);

	@Query("select p from Project p where p.id = :id")
	Project findProjectById(int id);
}
