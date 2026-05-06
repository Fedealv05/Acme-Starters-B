
package acme.features.member.invention.part;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.inventions.Invention;
import acme.entities.inventions.Part;
import acme.entities.projects.ProjectMember;

@Repository
public interface MemberInventionPartRepository extends AbstractRepository {

	List<Part> findByInventionId(int inventionId);

	@Query("select i from Invention i where i.id = :inventionId")
	Invention findInventionById(int inventionId);

	@Query("select pm from ProjectMember pm where pm.project.id = :projectId and pm.member.id = :memberId")
	ProjectMember findProjectMember(int projectId, int memberId);

	@Query("select p from Part p where p.id = :id")
	Part findPartById(int id);

}
