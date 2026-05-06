<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
    <acme:form-textbox code="member.spokesperson.form.label.username" path="userAccount.username" readonly="true"/>
    <acme:form-textbox code="member.spokesperson.form.label.cv" path="cv" readonly="true"/>
    <acme:form-textarea code="member.spokesperson.form.label.achievements" path="achievements" readonly="true"/>
    <acme:form-checkbox code="member.spokesperson.form.label.licensed" path="licensed" readonly="true"/>
    
</acme:form>