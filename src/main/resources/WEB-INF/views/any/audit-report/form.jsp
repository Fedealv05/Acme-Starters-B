<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="any.audit-report.form.label.ticker" path="ticker" readonly="true"/>
	<acme:form-textbox code="any.audit-report.form.label.name" path="name" readonly="true"/>
	<acme:form-textarea code="any.audit-report.form.label.description" path="description" readonly="true"/>
	<acme:form-moment code="any.audit-report.form.label.startMoment" path="startMoment" readonly="true"/>
	<acme:form-moment code="any.audit-report.form.label.endMoment" path="endMoment" readonly="true"/>
	<acme:form-url code="any.audit-report.form.label.moreInfo" path="moreInfo" readonly="true"/>
	<acme:form-double code="any.audit-report.form.label.monthsActive" path="monthsActive" readonly="true"/>
	<acme:form-integer code="any.audit-report.form.label.hours" path="hours" readonly="true"/>
	<jstl:if test="${title != null}">
	<acme:form-textbox code="any.audit-report.form.label.project" path="title" readonly="true"/>
	</jstl:if>
	
	
	<jstl:if test="${projectId != null && projectUnasssignMoment}">
		<acme:submit code="auditor.audit-report.button.unassign" action="/auditor/audit-report-project/unassign?id=${id}"/>
	</jstl:if>
</acme:form>