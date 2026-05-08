
package acme.features.auditor.campaign;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaigns.Campaign;

@Repository
public interface AuditorCampaignRepository extends AbstractRepository {

	@Query("select c from Campaign c where c.project.id = :projectId")
	List<Campaign> findCampaignsByProjectId(int projectId);

	@Query("select p from Campaign p where p.id = :id")
	Campaign findCampaignById(int id);
}
