
package acme.features.sponsor.project;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;

@Repository
public interface SponsorProjectRepository extends AbstractRepository {

	@Query("select p from Project p where p.draftMode = false")
	Collection<Project> findPublishedProjects();

	@Query("select p from Project p where p.id = :id")
	Project findOneProjectById(int id);

}
