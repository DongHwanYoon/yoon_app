<%@ tag body-content="scriptless" pageEncoding="utf-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="num1" required="true" type="java.lang.Integer" %>
<%@ attribute name="num2" required="true" type="java.lang.Integer" %>
<%@ variable name-given="result" variable-class="java.lang.Integer" scope="NESTED" %>

<c:set  var="result"  value="${'34' }" />
<jsp:doBody/>