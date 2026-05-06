
package acme.features.manager.tactic;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.strategy.Tactic;

@Repository
public interface ManagerTacticRepository extends AbstractRepository {

	@Query("select s from Tactic s where s.id = :id")
	Tactic findTacticById(int id);

	List<Tactic> findByStrategyId(int strategyId);

}
