<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div id="messages"></div>

<script type="text/javascript">
var timer;

function showReply(i) {
	var form = $("#form-" + i);
	form.toggle();
	if (form.is(':visible')) {
		stopTimer();
	} else {
		startTimer();
	}
}

function sendMessage(i, name, email) {
	var text = $('#textbox-' + i);
	var alert = $('#alert-' + i);
	var btn  = $('#replybutton-' + i);
	btn.prop('disabled', true);
	$.ajax({
		type: 'POST',
		url:'<c:url value="/sendmessage" />',
		data: JSON.stringify({text: text.val(), name: name, email: email,}),
		success: function(data) {
			showReply(i);
			alert.text('Message sent.');
			btn.prop('disabled', false);
			text.val('');
		},
		error: function(data) {
			alert("Send message error, please try it later");
		},
		contentType: 'application/json',
		dataType: 'json'
	});
}

function showMessages(data) {
	var messages = data.messages;
	
	$("#messages").html("");  // clear the div.
	
	for (var i = 0; i < messages.length; ++i) {
		var message = data.messages[i];
		
		var messageDiv = document.createElement("div");
		messageDiv.setAttribute("class", "message");
		
		var subjectSpan = document.createElement("span");
		subjectSpan.setAttribute("class", "subject");
		subjectSpan.appendChild(document.createTextNode(message.subject));
		
		var contentSpan = document.createElement("span");
		contentSpan.setAttribute("class", "messagebody");
		contentSpan.appendChild(document.createTextNode(message.content));
		
		var nameSpan = document.createElement("span");
		nameSpan.setAttribute("class", "nameemail");
		nameSpan.appendChild(document.createTextNode(message.name + "("));
		var link = document.createElement("a");
		link.setAttribute("class", "replylink");
		link.setAttribute("href", "#");
		link.setAttribute("onclick", "showReply(" + i +")");
		link.appendChild(document.createTextNode(message.email));
		nameSpan.appendChild(link);
		nameSpan.appendChild(document.createTextNode(")"));
		
		var alertSpan = document.createElement("span");
		alertSpan.setAttribute("class", "alert");
		alertSpan.setAttribute("id", "alert-" + i);
		
		var replyForm = document.createElement("form");
		replyForm.setAttribute("id", "form-" + i);
		replyForm.setAttribute("class", "replyform");
		
		var textarea = document.createElement("textarea");
		textarea.setAttribute("class", "replyarea");
		textarea.setAttribute('id', 'textbox-' + i);
		
		var replyButton = document.createElement("input");
		replyButton.setAttribute("class", "replybutton");
		replyButton.setAttribute("type", "button");
		replyButton.setAttribute("value", "Reply");
		replyButton.setAttribute("id", "replybutton-" + i);
		replyButton.onclick = function(j, name, email) {
			return function() {
				sendMessage(j, name, email);
			};
		}(i, message.name, message.email);
		
		replyForm.appendChild(textarea);
		replyForm.appendChild(replyButton);
		
		messageDiv.appendChild(subjectSpan);
		messageDiv.appendChild(contentSpan);
		messageDiv.appendChild(nameSpan);
		messageDiv.appendChild(alertSpan);
		messageDiv.appendChild(replyForm);
		
		$("#messages").append(messageDiv);
	}

}



function updatePage() {
	$.getJSON('<c:url value="/getmessages" />', showMessages);
}

function startTimer() {
	timer = window.setInterval(updatePage, 5000);
}

function stopTimer() {
	window.clearInterval(timer);
}

$(document).ready(function() {
	updatePage();
	startTimer();
});
</script>