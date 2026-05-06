
package acme.features.any.inventor;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.realms.Inventor;

@Repository
public interface AnyInventorRepository extends AbstractRepository {

	@Query("select i from Inventor i where i.id = :id")
	Inventor findInventorById(int id);

	@Query("select f from Inventor f where f.userAccount.id in (" + "select ua.id from ProjectMember mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	List<Inventor> findAssignedInventors(int projectId);
}
