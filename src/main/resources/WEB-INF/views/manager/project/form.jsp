<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<acme:form-textbox code="manager.project.form.label.title" path="title"/>
<acme:form-textbox code="manager.project.form.label.keyWords" path="keyWords"/>
<acme:form-textarea code="manager.project.form.label.description" path="description"/>
<acme:form-moment code="manager.project.form.label.kickOff" path="kickOff"/>
<acme:form-moment code="manager.project..form.label.closeOut" path="closeOut"/>

<acme:form-money code="inventor.invention.form.label.effort" path="effort" readonly="true"/>

<jstl:choose>
    <jstl:when test="${_command == 'show' && draftMode == false}">
        <acme:button code="manager.project.form.button.inventions" action="/manager/invention/list?projectId=${id}"/>
        <acme:button code="manager.project.form.button.campaigns" action="/manager/campaign/list?projectId=${id}"/>
        <acme:button code="manager.project.form.button.strategies" action="/manager/strategy/list?projectId=${id}"/>
    </jstl:when>
    <jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == true}">
<acme:button code="manager.project.form.button.inventions" action="/manager/invention/list?projectId=${id}"/>
        <acme:button code="manager.project.form.button.campaigns" action="/manager/campaign/list?projectId=${id}"/>
        <acme:button code="manager.project.form.button.strategies" action="/manager/strategy/list?projectId=${id}"/>
        <acme:submit code="manager.project.form.button.update" action="/manager/project/update"/>
        <acme:submit code="manager.project.form.button.delete" action="/manager/project/delete"/>
        <acme:submit code="manager.project.form.button.publish" action="/manager/project/publish"/>
    </jstl:when>
    <jstl:when test="${_command == 'create'}">
        <acme:submit code="manager.project.form.button.create" action="/manager/project/create"/>
    </jstl:when>
</jstl:choose>



</acme:form>