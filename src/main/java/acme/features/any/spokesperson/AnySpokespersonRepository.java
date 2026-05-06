
package acme.features.any.spokesperson;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.realms.Spokesperson;

@Repository
public interface AnySpokespersonRepository extends AbstractRepository {

	@Query("select i from Spokesperson i where i.id = :id")
	Spokesperson findSpokespersonById(int id);

	@Query("select f from Spokesperson f where f.userAccount.id in (" + "select ua.id from ProjectMember mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	List<Spokesperson> findAssignedSpokespersons(int projectId);

}
