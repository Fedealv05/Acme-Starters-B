
package acme.forms;

import javax.validation.constraints.NotNull;

import acme.client.components.basis.AbstractForm;
import acme.client.components.validation.Mandatory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventionAssignment extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	@Mandatory
	@NotNull
	public Integer				inventionId;

	@Mandatory
	@NotNull
	public Integer				projectId;

}
