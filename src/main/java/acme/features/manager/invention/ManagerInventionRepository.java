
package acme.features.manager.invention;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.inventions.Invention;

@Repository
public interface ManagerInventionRepository extends AbstractRepository {

	List<Invention> findByProjectId(int projectId);

	@Query("select i from Invention i where i.id = :id")
	Invention findInventionById(int id);

	Invention findByTicker(String ticker);

}
