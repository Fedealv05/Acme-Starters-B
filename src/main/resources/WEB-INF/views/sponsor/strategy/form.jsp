<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<acme:form-textbox code="sponsor.strategy.form.label.ticker" path="ticker" readonly="true"/>
<acme:form-textbox code="sponsor.strategy.form.label.name" path="name" readonly="true"/>
<acme:form-textarea code="sponsor.strategy.form.label.description" path="description" readonly="true"/>
<acme:form-textarea code="sponsor.strategy.form.label.moreInfo" path="moreInfo" readonly="true"/>
<acme:form-moment code="sponsor.strategy.form.label.startMoment" path="startMoment" readonly="true"/>
<acme:form-moment code="sponsor.strategy.form.label.endMoment" path="endMoment" readonly="true"/>
<acme:form-textarea code="sponsor.strategy.form.label.expectedPercentage" path="expectedPercentage" readonly="true"/>
<acme:form-textbox code="sponsor.strategy.form.label.monthsActive" path="monthsActive" readonly="true"/>

<acme:button code="sponsor.strategy.form.label.tactics" action="/manager/tactic/list?strategyId=${id}"  />

</acme:form>