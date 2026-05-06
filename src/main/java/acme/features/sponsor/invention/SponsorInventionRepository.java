package acme.features.sponsor.invention;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.inventions.Invention;

@Repository
public interface SponsorInventionRepository extends AbstractRepository {

	@Query("select i from Invention i where i.project.id = :projectId")
	List<Invention> findInventionsByProjectId(int projectId);
}