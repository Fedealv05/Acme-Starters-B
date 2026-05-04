<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
    <acme:form-textbox code="member.project.form.label.title" path="title" readonly="true"/>
    <acme:form-textbox code="member.project.form.label.keyWords" path="keyWords" readonly="true"/>
    <acme:form-textarea code="member.project.form.label.description" path="description" readonly="true"/>
    
    <acme:form-moment code="member.project.form.label.kickOff" path="kickOff" readonly="true"/>
    <acme:form-moment code="member.project.form.label.closeOut" path="closeOut" readonly="true"/>
    
 
    <acme:form-textbox code="member.project.form.label.effort" path="effort" readonly="true"/>
    <acme:form-textbox code="member.project.form.label.managerName" path="managerName" readonly="true"/>

    <jstl:if test="${_command == 'show'}">
        <br />
        <acme:button code="member.project.form.button.campaigns" action="/member/campaign/list?projectId=${id}"/>
        <acme:button code="member.project.form.button.inventions" action="/member/invention/list?projectId=${id}"/>
        <acme:button code="member.project.form.button.strategies" action="/member/strategy/list?projectId=${id}"/>
    </jstl:if>

</acme:form>