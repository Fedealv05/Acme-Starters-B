<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="auditor.project.list.label.title" path="title"/>
	<acme:list-column code="auditor.project.list.label.keyWords" path="keyWords"/>
	<acme:list-column code="auditor.project.list.label.kickOffMoment" path="kickOff"/>
	<acme:list-column code="auditor.project.list.label.closeOutMoment" path="closeOut"/>
</acme:list>