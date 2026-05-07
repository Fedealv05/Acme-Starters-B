<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-select code="inventor.invention-assignment.list" path="inventionId" choices="${listaInventions}"/>
	<jstl:if test="${_command == 'create' }">
        <acme:submit code="inventor.invention-assignment.button.create" action="/inventor/invention-assignment/create?projectId=${projectId}"/>
    </jstl:if>
</acme:form>