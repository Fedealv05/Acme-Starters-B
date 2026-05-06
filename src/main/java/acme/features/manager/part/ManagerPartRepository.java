
package acme.features.manager.part;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.inventions.Part;

@Repository
public interface ManagerPartRepository extends AbstractRepository {

	@Query("select s from Part s where s.id = :id")
	Part findPartById(int id);

	List<Part> findByInventionId(int inventionId);

}
