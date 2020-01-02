<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<fmt:setBundle basename="com.edu.bundle.msg"/>

<fmt:message var="name" key="name" >
   <fmt:param>Amy</fmt:param>
</fmt:message>


${name}
<br>

<fmt:formatNumber value="4578.8743" type="NUMBER" maxIntegerDigits="3"></fmt:formatNumber> <br>
<fmt:formatNumber value="4578.8743" type="NUMBER" minIntegerDigits="1"></fmt:formatNumber> <br> 
<fmt:formatNumber value="4578.8743" type="NUMBER" maxFractionDigits="3"></fmt:formatNumber> <br>
<fmt:formatNumber value="4578.8743" type="NUMBER" minFractionDigits="1"></fmt:formatNumber> <br>



