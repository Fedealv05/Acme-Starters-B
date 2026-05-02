
package acme.features.manager.project;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;

@Repository
public interface ManagerProjectRepository extends AbstractRepository {

	List<Project> findByManagerId(int managerId);

	@Query("select i from Project i where i.id = :id")
	Project findProjectById(int id);
}
