<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<acme:form-textbox code="any.project.form.label.title" path="title"/>
<acme:form-textbox code="any.project.form.label.keyWords" path="keyWords"/>
<acme:form-textarea code="any.project.form.label.description" path="description"/>
<acme:form-moment code="any.project.form.label.kickOff" path="kickOff"/>
<acme:form-moment code="any.project..form.label.closeOut" path="closeOut"/>

<acme:form-money code="any.invention.form.label.effort" path="effort" readonly="true"/>

        <acme:button code="any.project.form.button.inventions" action="/any/invention/list?projectId=${id}"/>
        <acme:button code="any.project.form.button.campaigns" action="/any/campaign/list?projectId=${id}"/>
        <acme:button code="any.project.form.button.strategies" action="/any/strategy/list?projectId=${id}"/>
        <acme:button code="any.project.form.button.auditReport" action="/any/audit-report/list?projectId=${id}"/>
         <acme:button code="any.project.form.button.sponsorship" action="/any/sponsorship/list?projectId=${id}"/>
    
        <acme:button code="any.inventor.form.button.list" action="/any/inventor/list?projectId=${id}"/>
        <acme:button code="any.fundraiser.form.button.list" action="/any/fundraiser/list?projectId=${id}"/>
        <acme:button code="any.spokesperson.form.button.list" action="/any/spokesperson/list?projectId=${id}"/>
        
<acme:button code="any.project.form.label.manager" action="/any/manager/show?id=${managerId}"  />




</acme:form>