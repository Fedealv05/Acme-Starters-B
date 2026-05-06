
package acme.features.fundraiser.strategy_assignment;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.projects.ProjectMember;
import acme.entities.strategy.Strategy;

@Repository
public interface FundraiserStrategyAssignmentRepository extends AbstractRepository {

	@Query("select p from Project p where p.id = :projectId")
	Project findProjectById(int projectId);

	@Query("select s from Strategy s where s.fundraiser.id = :fundraiserId and s.project is null")
	List<Strategy> findAvailableStrategiesByFundraiserId(int fundraiserId);

	@Query("select pm from ProjectMember pm where pm.project.id = :id and pm.member.id = :memberId")
	ProjectMember findProjectMember(int id, int memberId);

	@Query("select s from Strategy s where s.id = :strategyId")
	Strategy findStrategyById(Integer strategyId);

	@Query("select count(pm) from ProjectMember pm where pm.project.id = :id and pm.member.userAccount.id = :accountId")
	Integer checkProjectBelongsToAccountId(int id, int accountId);

}
