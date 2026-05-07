<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
    <acme:form-textbox code="member.campaign.form.label.ticker" path="ticker" readonly="true"/>
    <acme:form-textbox code="member.campaign.form.label.name" path="name" readonly="true"/>
    <acme:form-textarea code="member.campaign.form.label.description" path="description" readonly="true"/>
    <acme:form-url code="member.campaign.form.label.moreInfo" path="moreInfo" readonly="true"/>
    <acme:form-moment code="member.campaign.form.label.startMoment" path="startMoment" readonly="true"/>
    <acme:form-moment code="member.campaign.form.label.endMoment" path="endMoment" readonly="true"/>
    
    
    <acme:button code="member.campaign.milestone.form.button.milestones" action="/member/milestone/list?campaignId=${id}"/>
    <acme:button code="any.campaign.button.spokesperson" action="/any/spokesperson/show?id=${spokespersonId}"/>
    
    <jstl:if test="${projectId != null}">
		<acme:submit code="member.campaign.button.unassign" action="/spokesperson/campaign/unassign?campaignId=${id}"/>
	 </jstl:if>
</acme:form>