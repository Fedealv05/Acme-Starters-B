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
    
    <acme:form-textbox code="member.invention.form.label.inventorName" path="inventorName" readonly="true"/>
</acme:form>