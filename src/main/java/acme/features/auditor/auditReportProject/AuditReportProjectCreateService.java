
package acme.features.auditor.auditReportProject;

import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.audit_reports.AuditReport;
import acme.entities.projects.Project;
import acme.forms.AuditReportProject;
import acme.realms.Auditor;

@Service

public class AuditReportProjectCreateService extends AbstractService<Auditor, AuditReportProject> {

	@Autowired
	private AuditReportProjectRepository	repository;
	private AuditReportProject				auditReportProject;
	private Collection<AuditReport>			auditReports;
	private Project							project;


	@Override
	public void load() {
		Integer projectId = super.getRequest().getData("projectId", int.class);
		this.project = this.repository.findProjectById(projectId);
		int auditorId = super.getRequest().getPrincipal().getActiveRealm().getId();
		this.auditReports = this.repository.findAvailableAuditReportsByAuditorId(auditorId);
		this.auditReportProject = super.newObject(AuditReportProject.class);
		this.auditReportProject.setProjectId(projectId);

	}

	@Override
	public void authorise() {
		boolean status;
		status = this.project != null && !this.project.getDraftMode();
		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		int projectId = super.getRequest().getData("projectId", int.class);
		super.bindObject(this.auditReportProject, "auditReportId");
		this.auditReportProject.setProjectId(projectId);
	}

	@Override
	public void validate() {
		super.validateObject(this.auditReportProject);
		boolean hasAuditReport = this.auditReportProject.getAuditReportId() != 0;
		super.state(hasAuditReport, "auditReportId", "auditor.auditReportProject.error.sponsorshipId.required");
	}

	@Override
	public void execute() {
		AuditReport auditReport = this.repository.findAuditReportById(this.auditReportProject.getAuditReportId());
		if (auditReport != null) {
			Date projectUnassignMoment = MomentHelper.deltaFromCurrentMoment(24, ChronoUnit.HOURS);
			auditReport.setProjectUnassignMoment(projectUnassignMoment);
			auditReport.setProject(this.project);
			this.repository.save(auditReport);
		}
	}

	@Override
	public void unbind() {
		SelectChoices choices;
		choices = SelectChoices.from(this.auditReports, "ticker", null);
		super.unbindObject(this.auditReportProject, "auditReportId");
		super.unbindGlobal("listaAuditReports", choices);
		super.unbindGlobal("projectId", this.project.getId());
	}
}
