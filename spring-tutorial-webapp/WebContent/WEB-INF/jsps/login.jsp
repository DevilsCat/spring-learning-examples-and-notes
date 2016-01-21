<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Login Page</title>
<link href="${pageContext.request.contextPath}/static/css/main.css"
  rel="stylesheet" type="text/css" />
</head>
<body onload='document.f.username.focus();'>
  <h3>Login with Username and Password</h3>
  
  <c:if test="${param.error != null}">
  <span class="error">Login failed. Check that your username and password are correct.</span>
  </c:if>
  
  <form name='f' action='${pageContext.request.contextPath}/login'
    method='POST'>
    <table class="formtable">
      <tr>
        <td>User:</td>
        <td><input type='text' name='username' class="control"
          value=''></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type='password' name='password' class="control" /></td>
      </tr>
      <tr>
        <td colspan='2'><input name="submit" type="submit"
          class="control" value="Login" /></td>
      </tr>
      <tr>
        <td><input type="hidden" name="${_csrf.parameterName}"
          value="${_csrf.token}" /></td>
      </tr>
    </table>
  </form>
  
  <p><a href='<c:url value="/newaccount"/>'>Create New Account</a></p>
</body>
</html>