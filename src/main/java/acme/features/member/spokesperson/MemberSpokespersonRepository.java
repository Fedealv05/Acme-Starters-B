
package acme.features.member.spokesperson;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.ProjectMember;
import acme.realms.Spokesperson;

@Repository
public interface MemberSpokespersonRepository extends AbstractRepository {

	@Query("select s from Spokesperson s where s.userAccount.id in (select pm.member.userAccount.id from ProjectMember pm where pm.project.id = :projectId)")
	List<Spokesperson> findSpokespersonsByProjectId(int projectId);

	@Query("select pm from ProjectMember pm where pm.project.id = :projectId and pm.member.id = :memberId")
	ProjectMember findProjectMember(int projectId, int memberId);

	@Query("select s from Spokesperson s where s.id = :id")
	Spokesperson findSpokespersonById(int id);
}
