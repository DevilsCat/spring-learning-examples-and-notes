<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:choose>
<c:when test="${hasOffer}">
  <a href="${pageContext.request.contextPath}/createoffer">Edit or delete your current offer</a>
</c:when>
<c:otherwise>
<div><a href="${pageContext.request.contextPath}/createoffer">Add a new offer</a></div>
</c:otherwise>
</c:choose> 
<sec:authorize access="hasRole('ROLE_ADMIN')">
  <div><a href="${pageContext.request.contextPath}/admin">Admin</a></div>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
  <div><a href="<c:url value='/messages'/>">Messages (<span id="numberMessages">0</span>)</a></div>
</sec:authorize>