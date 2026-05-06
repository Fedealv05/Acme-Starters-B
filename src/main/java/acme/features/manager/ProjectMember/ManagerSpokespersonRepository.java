
package acme.features.manager.ProjectMember;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.realms.Spokesperson;

@Repository
public interface ManagerSpokespersonRepository extends AbstractRepository {

	@Query("select s from Spokesperson s where s.id = :id")
	Spokesperson findSpokespersonById(int id);
}
