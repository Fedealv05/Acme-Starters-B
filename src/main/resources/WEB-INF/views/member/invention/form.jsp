<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
    <acme:form-textbox code="member.invention.form.label.ticker" path="ticker" readonly="true"/>
    <acme:form-textbox code="member.invention.form.label.name" path="name" readonly="true"/>
    <acme:form-textarea code="member.invention.form.label.description" path="description" readonly="true"/>
    <acme:form-url code="member.invention.form.label.moreInfo" path="moreInfo" readonly="true"/>
    <acme:form-moment code="member.invention.form.label.startMoment" path="startMoment" readonly="true"/>
    <acme:form-moment code="member.invention.form.label.endMoment" path="endMoment" readonly="true"/>
    
    
    <acme:button code="member.invention.form.button.parts" action="/member/part/list?inventionId=${id}"/>
    <acme:button code="any.invention.button.inventor" action="/any/inventor/show?id=${inventorId}"/>
    
    <jstl:if test="${projectId != null}">
		<acme:submit code="member.invention.button.unassign" action="/inventor/invention/unassign?inventionId=${id}"/>
	</jstl:if>
    
</acme:form>