
package acme.features.member.project;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.projects.ProjectMember;

@Repository
public interface MemberProjectRepository extends AbstractRepository {

	@Query("select pm.project from ProjectMember pm where pm.member.id = :memberId")
	List<Project> findProjectsByMemberId(int memberId);

	@Query("select p from Project p where p.id = :id")
	Project findProjectById(int id);

	@Query("select pm from ProjectMember pm where pm.project.id = :projectId and pm.member.id = :memberId")
	ProjectMember findProjectMember(int projectId, int memberId);

}
