<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
<acme:list-column code="any.fundraiser.list.label.username" path="userAccount.username" width="25%" />
<acme:list-column code="any.fundraiser.list.label.bank" path="bank" width="25%" />
<acme:list-column code="any.fundraiser.list.label.agent" path="agent" width="25%" />


</acme:list>


