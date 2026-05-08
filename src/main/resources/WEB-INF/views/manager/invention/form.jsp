<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<acme:form-textbox code="manager.invention.form.label.ticker" path="ticker" readonly="true"/>
<acme:form-textbox code="manager.invention.form.label.name" path="name" readonly="true"/>
<acme:form-textarea code="manager.invention.form.label.description" path="description" readonly="true"/>
<acme:form-textarea code="manager.invention.form.label.moreInfo" path="moreInfo" readonly="true"/>
<acme:form-moment code="manager.invention.form.label.startMoment" path="startMoment" readonly="true"/>
<acme:form-moment code="manager.invention.form.label.endMoment" path="endMoment" readonly="true"/>
<acme:form-money code="manager.invention.form.label.cost" path="cost" readonly="true"/>
<acme:form-textbox code="manager.invention.form.label.monthsActive" path="monthsActive" readonly="true"/>

<acme:button code="manager.invention.form.label.parts" action="/manager/part/list?inventionId=${id}"  />
<acme:button code="any.campaign.form.label.inventor" action="/any/inventor/show?id=${inventorId}"/>


</acme:form>