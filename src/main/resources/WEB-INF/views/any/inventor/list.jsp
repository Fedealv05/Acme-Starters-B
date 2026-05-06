<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
<acme:list-column code="any.inventor.list.label.username" path="userAccount.username" width="25%" />
<acme:list-column code="any.inventor.list.label.keyWords" path="keyWords" width="25%" />
<acme:list-column code="any.inventor.list.label.licensed" path="licensed" width="25%" />
</acme:list>

