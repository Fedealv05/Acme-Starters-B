<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<acme:form-textbox code="manager.strategy.form.label.ticker" path="ticker" readonly="true"/>
<acme:form-textbox code="manager.strategy.form.label.name" path="name" readonly="true"/>
<acme:form-textarea code="manager.strategy.form.label.description" path="description" readonly="true"/>
<acme:form-textarea code="manager.strategy.form.label.moreInfo" path="moreInfo" readonly="true"/>
<acme:form-moment code="manager.strategy.form.label.startMoment" path="startMoment" readonly="true"/>
<acme:form-moment code="manager.strategy.form.label.endMoment" path="endMoment" readonly="true"/>
<acme:form-textarea code="manager.strategy.form.label.expectedPercentage" path="expectedPercentage" readonly="true"/>
<acme:form-textbox code="manager.strategy.form.label.monthsActive" path="monthsActive" readonly="true"/>

<acme:button code="manager.strategy.form.label.tactics" action="/manager/tactic/list?strategyId=${id}"  />

</acme:form>