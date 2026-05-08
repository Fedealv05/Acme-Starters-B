<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="manager.dashboard.totalProjects" path="totalProjects" readonly="true"/>
<acme:form-textbox code="manager.dashboard.devTotalProjects" path="devTotalProjects" readonly="true"/>
<acme:form-textbox code="manager.dashboard.minEffort" path="minEffort" readonly="true"/>
<acme:form-textbox code="manager.dashboard.maxEffort" path="maxEffort" readonly="true"/>
<acme:form-textbox code="manager.dashboard.avgEffort" path="avgEffort" readonly="true"/>
<acme:form-textbox code="manager.dashboard.devEffort" path="devEffort" readonly="true"/>
	
</acme:form>