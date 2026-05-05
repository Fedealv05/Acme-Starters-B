
package acme.features.manager.milestone;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaigns.Milestone;

@Repository
public interface ManagerMilestoneRepository extends AbstractRepository {

	@Query("select s from Milestone s where s.id = :id")
	Milestone findMilestoneById(int id);

	List<Milestone> findByCampaignId(int campaignId);

}
