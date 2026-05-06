
package acme.features.spokesperson.campaign_assignment;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaigns.Campaign;
import acme.entities.projects.Project;
import acme.entities.projects.ProjectMember;

@Repository
public interface SpokespersonCampaignAssignmentRepository extends AbstractRepository {

	@Query("select p from Project p where p.id = :projectId")
	Project findProjectById(int projectId);

	@Query("select c from Campaign c where c.spokesperson.id = :spokespersonId and c.project is null")
	List<Campaign> findAvailableCampaignsBySpokespersonId(int spokespersonId);

	@Query("select pm from ProjectMember pm where pm.project.id = :id and pm.member.id = :memberId")
	ProjectMember findProjectMember(int id, int memberId);

	@Query("select c from Campaign c where c.id = :campaignId")
	Campaign findCampaignById(Integer campaignId);

	@Query("select count(pm) from ProjectMember pm where pm.project.id = :id and pm.member.userAccount.id = :accountId")
	Integer checkProjectBelongsToAccountId(int id, int accountId);

}
