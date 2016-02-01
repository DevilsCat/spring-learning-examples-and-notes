<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
 
<!-- current offers for every one. -->
<table class="offers">
  <c:forEach var="offer" items="${offers}">
    <tr class="offerrow">
      <td class="name"><c:out value="${offer.user.name}"></c:out></td>
      <td class="contact"><a href="<c:url value='/message?uid=${offer.username}' />">contact</a></td>
      <td class="offer"><c:out value="${offer.text}"></c:out></td>
    </tr>
  </c:forEach>
</table>

<script type="text/javascript">

function updateMessageLink(data) {
  $("#numberMessages").text(data.number);
}

function onLoad() {
  $.getJSON('<c:url value="/getmessages" />', updateMessageLink);
  window.setInterval(function() {
    $.getJSON('<c:url value="/getmessages" />', updateMessageLink);
  }, 5000);
}

$(document).ready(onLoad);
</script>