
package acme.features.member.inventor;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.ProjectMember;
import acme.realms.Inventor;
import acme.realms.Member;

@Repository
public interface MemberInventorRepository extends AbstractRepository {

	@Query("select i from Inventor i where i.userAccount.id in (select pm.member.userAccount.id from ProjectMember pm where pm.project.id = :projectId)")
	List<Inventor> findInventorsByProjectId(int projectId);

	@Query("select pm from ProjectMember pm where pm.project.id = :projectId and pm.member.id = :memberId")
	ProjectMember findProjectMember(int projectId, int memberId);

	@Query("select i from Inventor i where i.id = :id")
	Inventor findInventorById(int id);

	@Query("select m from Member m where m.id = :id")
	Member findMemberbyId();

}
