
package acme.features.any.project;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;

@Repository
public interface AnyProjectRepository extends AbstractRepository {

	List<Project> findByDraftModeFalse();

	@Query("select i from Project i where i.id = :id")
	Project findProjectById(int id);
}
