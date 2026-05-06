<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
<acme:list-column code="member.tactic.list.label.name" path="name" width="10%" />
<acme:list-column code="member.tactic.list.label.expectedPercentage" path="expectedPercentage" width="20%" />
<acme:list-column code="member.tactic.list.label.kind" path="kind" width="20%" />

</acme:list>