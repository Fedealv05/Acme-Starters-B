
package acme.features.auditor.auditReportProject;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.audit_reports.AuditReport;
import acme.entities.projects.Project;

@Repository
public interface AuditReportProjectRepository extends AbstractRepository {

	@Query("select p from Project p where p.id = :id")
	Project findProjectById(int id);

	@Query("select s from AuditReport s where s.id = :id")
	AuditReport findAuditReportById(int id);

	@Query("""
			select s
			from AuditReport s
			where s.auditor.id = :auditorId
			and s.project is null
			and s.draftMode = false
		""")
	Collection<AuditReport> findAvailableAuditReportsByAuditorId(int auditorId);

}
