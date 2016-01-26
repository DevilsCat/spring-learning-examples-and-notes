<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
$(document).ready(function(){
	$('#delete').click(function (event) {
		var doDelete = confirm("Arm you sure you want to delete this offer?");
		if (doDelete == false) {
			event.preventDefault();
		}
	});
});
</script>

<!-- Make sure the fields match Offer properties. -->
<!-- Added commandName: model attribute. Controller creates a bean and "taglib" 
     saves the value to the object and retrieved it. -->
<sf:form method="post" action="${pageContext.request.contextPath}/docreate" commandName="offer">
<sf:input type="hidden" name="id" path="id"/>

<!-- Added spring validator, use "sf" as prefix. -->
<table class="formtable">
<tr><td class="label">Your offer: </td><td><sf:textarea class="control" path="text" name="text"></sf:textarea><br/><sf:errors path="text" cssClass="error"></sf:errors></td></tr>
<tr><td class="label"> </td><td><input class="control" value="Save advert" type="submit"></td></tr>

<!-- add delete offer function -->
<c:if test="${offer.id != 0}">
<tr><td class="label"> </td><td>&nbsp;</td></tr>
<tr><td class="label"> </td><td><input id="delete" class="control delete" name="delete" value="Delete this offer" type="submit"></td></tr>
</c:if>
</table>

</sf:form> 
