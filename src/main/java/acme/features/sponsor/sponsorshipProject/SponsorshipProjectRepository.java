
package acme.features.sponsor.sponsorshipProject;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.sponsorships.Sponsorship;

@Repository
public interface SponsorshipProjectRepository extends AbstractRepository {

	@Query("select p from Project p where p.id = :id")
	Project findProjectById(int id);

	@Query("select s from Sponsorship s where s.id = :id")
	Sponsorship findSponsorshipById(int id);

	@Query("""
			select s
			from Sponsorship s
			where s.sponsor.id = :sponsorId
			and s.project is null
			and s.draftMode = false
		""")
	Collection<Sponsorship> findAvailableSponsorshipsBySponsorId(int sponsorId);

}
