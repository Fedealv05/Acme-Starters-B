<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
    <acme:form-textbox code="member.fundraiser.form.label.username" path="userAccount.username" readonly="true"/>
    <acme:form-textbox code="member.fundraiser.form.label.bank" path="bank" readonly="true"/>
    <acme:form-textarea code="member.fundraiser.form.label.statement" path="statement" readonly="true"/>
    <acme:form-checkbox code="member.fundraiser.form.label.agent" path="agent" readonly="true"/>
    
</acme:form>