
package acme.forms;

import acme.client.components.basis.AbstractForm;
import acme.client.components.validation.Mandatory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditReportProject extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Mandatory

	public Integer				auditReportId;

	@Mandatory

	public Integer				projectId;

}
