<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-select code="spokesperson.campaign-assignment.list" path="campaignId" choices="${listaCampaigns}"/>
	<jstl:if test="${_command == 'create' }">
        <acme:submit code="spokesperson.campaign-assignment.button.create" action="/spokesperson/campaign-assignment/create?projectId=${projectId}"/>
    </jstl:if>
</acme:form>