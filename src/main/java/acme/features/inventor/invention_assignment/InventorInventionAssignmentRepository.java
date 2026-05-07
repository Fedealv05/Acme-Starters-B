
package acme.features.inventor.invention_assignment;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.inventions.Invention;
import acme.entities.projects.Project;
import acme.entities.projects.ProjectMember;

@Repository
public interface InventorInventionAssignmentRepository extends AbstractRepository {

	@Query("select p from Project p where p.id = :projectId")
	Project findProjectById(int projectId);

	@Query("select i from Invention i where i.inventor.id = :inventorId and i.project is null")
	List<Invention> findAvailableInventionsByInventorId(int inventorId);

	@Query("select pm from ProjectMember pm where pm.project.id = :id and pm.member.id = :memberId")
	ProjectMember findProjectMember(int id, int memberId);

	@Query("select i from Invention i where i.id = :inventionId")
	Invention findInventionById(Integer inventionId);

	@Query("select count(pm) from ProjectMember pm where pm.project.id = :id and pm.member.userAccount.id = :accountId")
	Integer checkProjectBelongsToAccountId(int id, int accountId);

}
