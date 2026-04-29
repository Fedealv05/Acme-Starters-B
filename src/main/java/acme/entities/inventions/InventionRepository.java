
package acme.entities.inventions;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface InventionRepository extends AbstractRepository {

	Invention findInventionByTicker(String ticker);

	@Query("select count(i) from Invention i where i.project.id = ?1")
	Long countByProjectId(int projectId);

	@Query("select i from Invention i where i.project.id = ?1")
	Collection<Invention> findByProjectId(int projectId);
}
