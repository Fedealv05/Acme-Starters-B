
package acme.features.any.audit_report;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.audit_reports.AuditReport;
import acme.entities.projects.Project;
import acme.features.any.project.AnyProjectRepository;
import acme.realms.Auditor;

@Service
public class AnyAuditReportListService extends AbstractService<Any, AuditReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyAuditReportRepository	repository;
	private Collection<AuditReport>		auditReports;
	private Project						project;
	@Autowired
	private AnyProjectRepository		projectRepository;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		if (super.getRequest().hasData("projectId")) {
			Integer projectId = super.getRequest().getData("projectId", Integer.class);
			this.auditReports = this.repository.findAllAuditReportsByProjectId(projectId);
			this.project = this.projectRepository.findProjectById(projectId);
		} else
			this.auditReports = this.repository.findPublishedAuditReports();
	}

	@Override
	public void authorise() {
		boolean status = true;
		if (super.getRequest().hasData("projectId") && this.project == null)
			status = false;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.auditReports, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "monthsActive", "hours");
		boolean isAuditor = super.getRequest().getPrincipal().getRealms().stream().anyMatch(Auditor.class::isInstance);
		if (super.getRequest().hasData("projectId")) {
			super.unbindGlobal("isAuditor", isAuditor);
			super.unbindGlobal("projectId", this.project.getId());
		}
	}

}
