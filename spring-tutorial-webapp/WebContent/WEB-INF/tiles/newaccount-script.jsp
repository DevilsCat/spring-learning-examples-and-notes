<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">

$(document).ready(function() {
	$('#password').keyup(checkPasswordMatch);
	$('#confirmpass').keyup(checkPasswordMatch);
	$('#details').submit(cantSubmit);
});

function cantSubmit() {
	var password = $('#password').val();
	var confirmpass = $('#confirmpass').val();
	
	if (password !== confirmpass) {
		return false;
	} else {
		return true;
	}
}

function checkPasswordMatch() {
	var password = $('#password').val();
	var confirmpass = $('#confirmpass').val();
	
	if (password.length > 3 || confirmpass.length > 3) {
		if (password == confirmpass) {
			$('#matchpass').text('Password match.');
			$('#matchpass').addClass('valid');
			$('#matchpass').removeClass('error');
		} else {
			$('#matchpass').text('Password do not match.');
			$('#matchpass').addClass('error');
			$('#matchpass').removeClass('valid');
		}
	}

}

</script>