
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<acme:form-textbox code="sponsor.invention.form.label.ticker" path="ticker" readonly="true"/>
<acme:form-textbox code="sponsor.invention.form.label.name" path="name" readonly="true"/>
<acme:form-textarea code="sponsor.invention.form.label.description" path="description" readonly="true"/>
<acme:form-textarea code="sponsor.invention.form.label.moreInfo" path="moreInfo" readonly="true"/>
<acme:form-moment code="sponsor.invention.form.label.startMoment" path="startMoment" readonly="true"/>
<acme:form-moment code="sponsor.invention.form.label.endMoment" path="endMoment" readonly="true"/>
<acme:form-money code="sponsor.invention.form.label.cost" path="cost" readonly="true"/>
<acme:form-textbox code="sponsor.invention.form.label.monthsActive" path="monthsActive" readonly="true"/>

<acme:button code="sponsor.invention.form.label.parts" action="/manager/part/list?inventionId=${id}"  />

</acme:form>