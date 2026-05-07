
package acme.features.auditor.auditReportProject;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.forms.AuditReportProject;
import acme.realms.Auditor;

@Controller
public class AuditReportProjectController extends AbstractController<Auditor, AuditReportProject> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("create", AuditReportProjectCreateService.class);

		super.addCustomCommand("unassign", "update", AuditReportProjectUnassignService.class);
	}

}
