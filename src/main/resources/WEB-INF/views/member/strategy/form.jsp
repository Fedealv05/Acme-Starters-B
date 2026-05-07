<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
    <acme:form-textbox code="member.strategy.form.label.ticker" path="ticker" readonly="true"/>
    <acme:form-textbox code="member.strategy.form.label.name" path="name" readonly="true"/>
    <acme:form-textarea code="member.strategy.form.label.description" path="description" readonly="true"/>
    <acme:form-url code="member.strategy.form.label.moreInfo" path="moreInfo" readonly="true"/>
    <acme:form-moment code="member.strategy.form.label.startMoment" path="startMoment" readonly="true"/>
    <acme:form-moment code="member.strategy.form.label.endMoment" path="endMoment" readonly="true"/>
    
    
    <acme:button code="member.strategy.form.button.tactics" action="/member/tactic/list?strategyId=${id}"/>
    <acme:button code="any.strategy.form.label.fundraiser" action="/any/fundraiser/show?id=${fundraiserId}"/>
    
     <jstl:if test="${projectId != null}">
		<acme:submit code="member.strategy.button.unassign" action="/fundraiser/strategy/unassign?strategyId=${id}"/>
	</jstl:if>  
</acme:form>