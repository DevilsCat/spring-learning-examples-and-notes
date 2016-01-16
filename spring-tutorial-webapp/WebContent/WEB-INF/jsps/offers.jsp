<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Offers</title>
</head>
<body>
  <b>Offers Table</b>
  <sql:query var="rs" dataSource="jdbc/spring">
		select id, name, email, text from offers
	</sql:query>

  <table border="1">
    <tr>
      <td>id</td>
      <td>name</td>
      <td>email</td>
      <td>text</td>
    </tr>
    <c:forEach var="row" items="${rs.rows}">
      <tr>
        <td>${row.id}</td>
        <td>${row.name}</td>
        <td>${row.email}</td>
        <td>${row.text}</td>
      </tr>
    </c:forEach>
  </table>

  <br />

  <b>Offers Table Use DAO</b>
  <br />
  <c:forEach var="offer" items="${offers}">
    <p><c:out value="${offer}"></c:out></p>
  </c:forEach>
</body>
</html>