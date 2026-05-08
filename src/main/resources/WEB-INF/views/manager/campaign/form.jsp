<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<acme:form-textbox code="manager.campaign.form.label.ticker" path="ticker" readonly="true"/>
<acme:form-textbox code="manager.campaign.form.label.name" path="name" readonly="true"/>
<acme:form-textarea code="manager.campaign.form.label.description" path="description" readonly="true"/>
<acme:form-textarea code="manager.campaign.form.label.moreInfo" path="moreInfo" readonly="true"/>
<acme:form-moment code="manager.campaign.form.label.startMoment" path="startMoment" readonly="true"/>
<acme:form-moment code="manager.campaign.form.label.endMoment" path="endMoment" readonly="true"/>
<acme:form-money code="manager.campaign.form.label.cost" path="cost" readonly="true"/>
<acme:form-textbox code="manager.campaign.form.label.monthsActive" path="monthsActive" readonly="true"/>

<acme:button code="manager.campaign.form.label.milestones" action="/manager/milestone/list?campaignId=${id}"  />
<acme:button code="any.campaign.form.label.spokesperson" action="/any/spokesperson/show?id=${spokespersonId}"/>

</acme:form>