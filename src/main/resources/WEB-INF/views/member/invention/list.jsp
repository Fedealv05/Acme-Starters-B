<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
    <acme:list-column code="member.invention.list.label.ticker" path="ticker" width="10%" />
    <acme:list-column code="member.invention.list.label.name" path="name" width="50%" />
    <acme:list-column code="member.invention.list.label.startMoment" path="startMoment" width="20%" />
    <acme:list-column code="member.invention.list.label.endMoment" path="endMoment" width="20%" />
</acme:list>

<jstl:if test="${isInventor && draftMode}">
     <acme:button code="member.project.button.inventor.create" action="/inventor/invention-assignment/create?projectId=${projectId}"/>
</jstl:if>