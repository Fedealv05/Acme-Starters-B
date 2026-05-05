<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="manager.tactic.form.label.name" path="name" readonly="true"/>
	<acme:form-textarea code="manager.tactic.form.label.notes" path="notes" readonly="true"/>
	<acme:form-textbox code="manager.tactic.form.label.expectedPercentage" path="expectedPercentage" readonly="true"/>
	<acme:form-textbox code="manager.tactic.form.label.kind" path="kind" readonly="true" />


</acme:form>