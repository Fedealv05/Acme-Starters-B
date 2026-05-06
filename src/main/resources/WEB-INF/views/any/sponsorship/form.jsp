<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<acme:form-textbox code="any.sponsorship.form.label.ticker" path="ticker" readonly="true"/>
<acme:form-textbox code="any.sponsorship.form.label.name" path="name" readonly="true"/>
<acme:form-textarea code="any.sponsorship.form.label.description" path="description" readonly="true"/>
<acme:form-textarea code="any.sponsorship.form.label.moreInfo" path="moreInfo" readonly="true"/>
<acme:form-moment code="any.sponsorship.form.label.startMoment" path="startMoment" readonly="true"/>
<acme:form-moment code="any.sponsorship.form.label.endMoment" path="endMoment" readonly="true"/>
<acme:form-money code="any.sponsorship.form.label.totalMoney" path="totalMoney" readonly="true"/>
<acme:form-textbox code="any.sponsorship.form.label.monthsActive" path="monthsActive" readonly="true"/>

<acme:button code="any.sponsorship.form.label.donations" action="/any/donation/list?sponsorshipId=${id }"/>

<acme:button code="any.sponsorship.form.label.sponsor" action="/any/sponsor/show?id=${sponsorId }"/>

<jstl:if test="${projectId != null && projectUnasssignMoment}">
		<acme:submit code="sponsor.sponsorship.button.unassign" action="/sponsor/sponsorship-project/unassign?id=${id}"/>
	</jstl:if>




</acme:form>