<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-select code="sponsor.sponsorship-assignment.list" path="sponsorshipId" choices="${listaSponsorships}"/>
	<jstl:if test="${_command == 'create' }">
        <acme:submit code="sponsor.sponsorship-assignment.button.create" action="/sponsor/sponsorship-project/create?projectId=${projectId}"/>
    </jstl:if>
</acme:form>