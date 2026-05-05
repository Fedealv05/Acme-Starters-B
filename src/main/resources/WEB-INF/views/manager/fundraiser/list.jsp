<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
<acme:list-column code="manager.fundraiser.list.label.username" path="userAccount.username" width="25%" />
<acme:list-column code="manager.fundraiser.list.label.bank" path="bank" width="25%" />
<acme:list-column code="manager.fundraiser.list.label.agent" path="agent" width="25%" />


</acme:list>

<acme:button code="manager.project.list.button.addMember" action="/manager/project-member/create?projectId=${projectId}&role=FUNDRAISER"/>
<acme:button code="manager.project.list.button.deleteMember" action="/manager/project-member/delete?projectId=${projectId}&role=FUNDRAISER"/>

