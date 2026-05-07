
package acme.features.auditor.auditReportProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.audit_reports.AuditReport;
import acme.forms.AuditReportProject;
import acme.realms.Auditor;

@Service
public class AuditReportProjectUnassignService extends AbstractService<Auditor, AuditReportProject> {

	@Autowired
	private AuditReportProjectRepository	repository;

	private AuditReportProject				auditReportProject;


	@Override
	public void load() {
		// Capturamos el ID de la auditoria que vamos a actualizar
		Integer auditReportId = super.getRequest().getData("id", Integer.class);

		this.auditReportProject = super.newObject(AuditReportProject.class);
		if (auditReportId != null)
			this.auditReportProject.setAuditReportId(auditReportId);
	}

	@Override
	public void authorise() {
		AuditReport auditReport = this.repository.findAuditReportById(this.auditReportProject.getAuditReportId());
		boolean status = auditReport != null;
		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		// No bind needed - the sponsorshipId comes from the query parameter in load()
	}

	@Override
	public void validate() {
		// Validate that we have the required sponsorship ID
		boolean hasAuditReport = this.auditReportProject.getAuditReportId() != 0 && this.auditReportProject.getAuditReportId() != null;
		super.state(hasAuditReport, "auditReportId", "auditor.auditReportProject.error.auditReportId.required");
	}

	@Override
	public void execute() {
		// 1. Buscamos el patrocinio en la base de datos
		AuditReport auditReport = this.repository.findAuditReportById(this.auditReportProject.getAuditReportId());

		if (auditReport != null) {
			// 2. EL ENFOQUE UPDATE: Ponemos el proyecto a null
			auditReport.setProject(null);

			// 3. Guardamos (Spring Data hará un UPDATE automático en la BBDD)
			this.repository.save(auditReport);
		}
	}

	@Override
	public void unbind() {
		super.unbindObject(this.auditReportProject);
		super.unbindGlobal("auditReportId", this.auditReportProject.getAuditReportId());
	}
}
