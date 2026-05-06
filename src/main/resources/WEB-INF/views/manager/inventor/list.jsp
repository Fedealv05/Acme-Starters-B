<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
<acme:list-column code="manager.inventor.list.label.username" path="userAccount.username" width="25%" />
<acme:list-column code="manager.inventor.list.label.keyWords" path="keyWords" width="25%" />
<acme:list-column code="manager.inventor.list.label.licensed" path="licensed" width="25%" />
</acme:list>

<acme:button code="manager.inventor.list.button.addMember" action="/manager/project-member/create?projectId=${projectId}&role=INVENTOR"/>
<acme:button code="manager.inventor.list.button.deleteMember" action="/manager/project-member/delete?projectId=${projectId}&role=INVENTOR"/>