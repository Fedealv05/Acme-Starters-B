<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<acme:form-select code="manager.projectmember.form.label.userChoices" path="selectedUser" choices="${userChoices}"/>


<jstl:choose>
    <jstl:when test="${_command == 'create'}">
        <acme:submit code="manager.project.list.button.addMember" action="/manager/project-member/create?projectId=${projectId}&role=${role}"/>
    </jstl:when>
    <jstl:when test="${_command == 'delete'}">
        <acme:submit code="manager.project.list.button.deleteMember" action="/manager/project-member/delete?projectId=${projectId}&role=${role}"/>
    </jstl:when>
</jstl:choose>

</acme:form>