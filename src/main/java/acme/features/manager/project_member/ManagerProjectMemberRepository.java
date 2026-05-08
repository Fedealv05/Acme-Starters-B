
package acme.features.manager.project_member;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.components.principals.UserAccount;
import acme.client.repositories.AbstractRepository;
import acme.entities.projects.ProjectMember;
import acme.realms.Fundraiser;
import acme.realms.Inventor;
import acme.realms.Member;
import acme.realms.Spokesperson;

@Repository
public interface ManagerProjectMemberRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findUserAccountById(int id);

	@Query("select pm.member from ProjectMember pm where pm.project.id = :projectId")
	List<Member> findMembersByProjectId(int projectId);

	@Query("select ua from UserAccount ua")
	List<UserAccount> findAllUserAccounts();

	@Query("select f from Inventor f where f.userAccount.id not in (" + "select ua.id from ProjectMember mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	List<Inventor> findUnassignedInventors(int projectId);

	@Query("select f from Fundraiser f where f.userAccount.id not in (" + "select ua.id from ProjectMember mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	List<Fundraiser> findUnassignedFundraisers(int projectId);

	@Query("select f from Spokesperson f where f.userAccount.id not in (" + "select ua.id from ProjectMember mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	List<Spokesperson> findUnassignedSpokespersons(int projectId);

	@Query("select f from Inventor f where f.userAccount.id in (" + "select ua.id from ProjectMember mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	List<Inventor> findAssignedInventors(int projectId);

	@Query("select f from Fundraiser f where f.userAccount.id in (" + "select ua.id from ProjectMember mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	List<Fundraiser> findAssignedFundraisers(int projectId);

	@Query("select f from Spokesperson f where f.userAccount.id in (" + "select ua.id from ProjectMember mp " + "join mp.member m " + "join m.userAccount ua " + "where mp.project.id = :projectId" + ")")
	List<Spokesperson> findAssignedSpokesperson(int projectId);

	@Query("select m from Member m where m.userAccount.id = :userAccountId")
	Member findMemberByUserAccountId(int userAccountId);

	@Query("select pm from ProjectMember pm where pm.project.id = :projectId and pm.member.id = :memberId")
	ProjectMember findProjectMemberByProjectIdAndMemberId(int projectId, int memberId);

	@Query("select pm from ProjectMember pm where pm.project.id = :projectId")
	List<ProjectMember> findByProjectId(int projectId);

}
