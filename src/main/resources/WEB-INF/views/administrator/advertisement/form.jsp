<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="administrator.banner.form.label.slogan" path="slogan"/>
	<acme:form-url code="administrator.banner.form.label.picture" path="picture"/>
	<acme:form-url code="administrator.banner.form.label.target" path="target"/>
	
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish')}">
			<acme:submit code="administrator.banner.form.button.update" action="/administrator/advertisement/update"/>
			<acme:submit code="administrator.banner.form.button.delete" action="/administrator/advertisement/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="administrator.banner.form.button.create" action="/administrator/advertisement/create"/>
		</jstl:when>	
	</jstl:choose>
</acme:form>