<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
<acme:list-column code="manager.spokesperson.list.label.username" path="userAccount.username" width="25%" />
<acme:list-column code="manager.spokesperson.list.label.cv" path="cv" width="25%" />
<acme:list-column code="manager.spokesperson.list.label.licensed" path="licensed" width="25%" />
</acme:list>

<acme:button code="manager.project.list.button.addMember" action="/manager/project-member/create?projectId=${projectId}&role=SPOKESPERSON"/>
<acme:button code="manager.project.list.button.deleteMember" action="/manager/project-member/delete?projectId=${projectId}&role=SPOKESPERSON"/>