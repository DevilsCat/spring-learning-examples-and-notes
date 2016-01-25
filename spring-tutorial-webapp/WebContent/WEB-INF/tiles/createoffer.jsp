<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!-- Make sure the fields match Offer properties. -->
<!-- Added commandName: model attribute. Controller creates a bean and "taglib" 
     saves the value to the object and retrieved it. -->
<sf:form method="post" action="${pageContext.request.contextPath}/docreate" commandName="offer">

<!-- Added spring validator, use "sf" as prefix. -->
<table class="formtable">
<tr><td class="label">Your offer: </td><td><sf:textarea class="control" path="text" name="text"></sf:textarea><br/><sf:errors path="text" cssClass="error"></sf:errors></td></tr>
<tr><td class="label"> </td><td><input class="control" value="Create advert" type="submit"></td></tr>
</table>

</sf:form> 
