<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>


<acme:form>
<acme:form-textbox code="manager.inventor.form.label.userAccount.username" path="userAccount.username" readonly="true"/>
<acme:form-textarea code="manager.inventor.form.label.bio" path="bio" readonly="true"/>
<acme:form-textarea code="manager.inventor.form.label.keyWords" path="keyWords" readonly="true"/>
<acme:form-textbox code="manager.inventor.form.label.licensed" path="licensed" readonly="true"/>

</acme:form>