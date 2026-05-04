<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="manager.dashboard.label" path="totalProjects" readonly = "true"/>
	<acme:form-textbox code="manager.dashboard.label" path="devTotalProjects" readonly = "true"/>
	<acme:form-textbox code="manager.dashboard.label" path="minEffort" readonly = "true"/>
	<acme:form-textbox code="manager.dashboard.label" path="maxEffort" readonly = "true"/>
	<acme:form-textbox code="manager.dashboard.label" path="avgEffort" readonly = "true"/>
	<acme:form-textbox code="manager.dashboard.label" path="devEffort" readonly = "true"/>
	
</acme:form>