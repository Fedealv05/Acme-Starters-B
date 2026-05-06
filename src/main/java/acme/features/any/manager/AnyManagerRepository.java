
package acme.features.any.manager;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.realms.Manager;

@Repository
public interface AnyManagerRepository extends AbstractRepository {

	@Query("select i from Manager i where i.id = :id")
	Manager findManagerById(int id);

}
