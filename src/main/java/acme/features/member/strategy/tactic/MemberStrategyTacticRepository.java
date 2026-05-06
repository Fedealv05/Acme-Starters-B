
package acme.features.member.strategy.tactic;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.ProjectMember;
import acme.entities.strategy.Strategy;
import acme.entities.strategy.Tactic;

@Repository
public interface MemberStrategyTacticRepository extends AbstractRepository {

	List<Tactic> findByStrategyId(int strategyId);

	@Query("select s from Strategy s where s.id = :strategyId")
	Strategy findStrategyById(int strategyId);

	@Query("select pm from ProjectMember pm where pm.project.id = :projectId and pm.member.id = :memberId")
	ProjectMember findProjectMember(int projectId, int memberId);

	@Query("select t from Tactic t where t.id = :id")
	Tactic findTacticById(int id);

}
