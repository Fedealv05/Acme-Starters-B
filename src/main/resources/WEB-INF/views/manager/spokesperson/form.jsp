<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>


<acme:form>
<acme:form-textbox code="manager.spokesperson.form.label.userAccount.username" path="userAccount.username" readonly="true"/>
<acme:form-textarea code="manager.spokesperson.form.label.cv" path="cv" readonly="true"/>
<acme:form-textarea code="manager.spokesperson.form.label.achievements" path="achievements" readonly="true"/>
<acme:form-textbox code="manager.spokesperson.form.label.licensed" path="licensed" readonly="true"/>

</acme:form>