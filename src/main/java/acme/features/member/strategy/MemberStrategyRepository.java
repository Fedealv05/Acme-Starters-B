
package acme.features.member.strategy;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.ProjectMember;
import acme.entities.strategy.Strategy;

@Repository
public interface MemberStrategyRepository extends AbstractRepository {

	@Query("select s from Strategy s where s.project.id = :projectId")
	List<Strategy> findStrategiesByProjectId(int projectId);

	@Query("select s from Strategy s where s.id = :id")
	Strategy findStrategyById(int id);

	@Query("select pm from ProjectMember pm where pm.project.id = :projectId and pm.member.id = :memberId")
	ProjectMember findProjectMember(int projectId, int memberId);

}
