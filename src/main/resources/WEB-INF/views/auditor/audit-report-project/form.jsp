<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-select code="auditor.audit-report-assignment.list" path="auditReportId" choices="${listaAuditReports}"/>
	<jstl:if test="${_command == 'create' }">
        <acme:submit code="auditor.audit-report-assignment.button.create" action="/auditor/audit-report-project/create?projectId=${projectId}"/>
    </jstl:if>
</acme:form>