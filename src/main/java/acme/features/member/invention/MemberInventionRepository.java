
package acme.features.member.invention;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.inventions.Invention;
import acme.entities.projects.ProjectMember;

@Repository
public interface MemberInventionRepository extends AbstractRepository {

	@Query("select i from Invention i where i.project.id = :projectId")
	List<Invention> findInventionsByProjectId(int projectId);

	@Query("select i from Invention i where i.id = :id")
	Invention findInventionById(int id);

	@Query("select pm from ProjectMember pm where pm.project.id = :projectId and pm.member.id = :memberId")
	ProjectMember findProjectMember(int projectId, int memberId);

}
