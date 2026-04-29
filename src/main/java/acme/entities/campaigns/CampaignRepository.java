
package acme.entities.campaigns;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface CampaignRepository extends AbstractRepository {

	Campaign findCampaignByTicker(String ticker);

	@Query("select c from Campaign c where c.project.id = ?1")
	Collection<Campaign> findByProjectId(int projectId);

}
