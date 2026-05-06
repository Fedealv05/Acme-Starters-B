
package acme.features.member.fundraiser;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.ProjectMember;
import acme.realms.Fundraiser;

@Repository
public interface MemberFundraiserRepository extends AbstractRepository {

	@Query("select f from Fundraiser f where f.userAccount.id in (select pm.member.userAccount.id from ProjectMember pm where pm.project.id = :projectId)")
	List<Fundraiser> findFundraisersByProjectId(int projectId);

	@Query("select pm from ProjectMember pm where pm.project.id = :projectId and pm.member.id = :memberId")
	ProjectMember findProjectMember(int projectId, int memberId);

	@Query("select f from Fundraiser f where f.id = :id")
	Fundraiser findFundraiserById(int id);

}
