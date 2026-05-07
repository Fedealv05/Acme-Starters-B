
package acme.features.auditor.strategy;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.strategy.Strategy;

@Repository
public interface AuditorStrategyRepository extends AbstractRepository {

	@Query("select s from Strategy s where s.project.id = :projectId")
	List<Strategy> findStrategiesByProjectId(int projectId);
}
