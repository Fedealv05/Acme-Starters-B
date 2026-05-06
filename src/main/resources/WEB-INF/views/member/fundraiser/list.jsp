<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
    <acme:list-column code="member.fundraiser.list.label.username" path="userAccount.username" width="10%" />
    <acme:list-column code="member.fundraiser.list.label.bank" path="bank" width="50%" />
    <acme:list-column code="member.fundraiser.list.label.agent" path="agent" width="20%" />
</acme:list>