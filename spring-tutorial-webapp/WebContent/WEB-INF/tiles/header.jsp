<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<a class="title" href="${pageContext.request.contextPath}/">Offers</a>

<sec:authorize access="!isAuthenticated()">
<a class="login" href="${pageContext.request.contextPath}/login">Login</a>
</sec:authorize>

<!-- Security authorization makes the "Log out" link appear when
       user has already logged in -->
<sec:authorize access="isAuthenticated()">
<a class="login" href="<c:url value='/j_spring_security_logout'/>">Log out</a>
</sec:authorize>