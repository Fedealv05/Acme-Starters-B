
package acme.features.member.campaign;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaigns.Campaign;
import acme.entities.projects.ProjectMember;

@Repository
public interface MemberCampaignRepository extends AbstractRepository {

	@Query("select c from Campaign c where c.project.id = :projectId")
	List<Campaign> findCampaignsByProjectId(int projectId);

	@Query("select c from Campaign c where c.id = :id")
	Campaign findCampaignById(int id);

	@Query("select pm from ProjectMember pm where pm.project.id = :projectId and pm.member.id = :memberId")
	ProjectMember findProjectMember(int projectId, int memberId);

}
