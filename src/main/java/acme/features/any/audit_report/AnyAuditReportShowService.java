
package acme.features.any.audit_report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.audit_reports.AuditReport;

@Service
public class AnyAuditReportShowService extends AbstractService<Any, AuditReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyAuditReportRepository	repository;

	private AuditReport					auditReport;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.auditReport = this.repository.findAuditReportById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.auditReport != null && !this.auditReport.getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.auditReport, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "monthsActive", "hours", "auditor");
		if (this.auditReport.getProject() != null) {
			super.unbindGlobal("title", this.auditReport.getProject().getTitle());
			if (this.auditReport.getAuditor().getUserAccount().getId() == super.getRequest().getPrincipal().getAccountId()) {
				super.unbindGlobal("projectId", this.auditReport.getProject().getId());
				if (MomentHelper.getCurrentMoment().before(this.auditReport.getProjectUnassignMoment()))
					super.unbindGlobal("projectUnasssignMoment", true);
			}
		}

	}

}
