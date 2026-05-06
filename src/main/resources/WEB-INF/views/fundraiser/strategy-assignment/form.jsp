<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-select code="fundraiser.strategy-assignment.list" path="strategyId" choices="${listaStrategies}"/>
	<jstl:if test="${_command == 'create' }">
        <acme:submit code="fundraiser.strategy-assignment.button.create" action="/fundraiser/strategy-assignment/create?projectId=${projectId}"/>
    </jstl:if>
</acme:form>