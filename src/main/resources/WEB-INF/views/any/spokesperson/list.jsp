<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
<acme:list-column code="any.spokesperson.list.label.username" path="userAccount.username" width="25%" />
<acme:list-column code="any.spokesperson.list.label.cv" path="cv" width="25%" />
<acme:list-column code="any.spokesperson.list.label.licensed" path="licensed" width="25%" />
</acme:list>

