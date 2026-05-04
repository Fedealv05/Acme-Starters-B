
package acme.features.manager.strategy;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.strategy.Strategy;

@Repository
public interface ManagerStrategyRepository extends AbstractRepository {

	List<Strategy> findByProjectId(int projectId);

	@Query("select i from Strategy i where i.id = :id")
	Strategy findStrategyById(int id);

}
