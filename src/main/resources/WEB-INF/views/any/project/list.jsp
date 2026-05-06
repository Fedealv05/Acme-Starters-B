<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
<acme:list-column code="any.project.list.label.title" path="title" width="25%" />
<acme:list-column code="any.project.list.label.keyWords" path="keyWords" width="25%" />
<acme:list-column code="any.project.list.label.kickOff" path="kickOff" width="25%" />
<acme:list-column code="any.project.list.label.closeOut" path="closeOut" width="25%" />
</acme:list>

