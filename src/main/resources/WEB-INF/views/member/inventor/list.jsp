<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
    <acme:list-column code="member.inventor.list.label.username" path="userAccount.username" width="10%" />
    <acme:list-column code="member.inventor.list.label.bio" path="bio" width="50%" />
    <acme:list-column code="member.inventor.list.label.licensed" path="licensed" width="20%" />
</acme:list>