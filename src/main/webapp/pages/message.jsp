<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>menu de gestion</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<c:url value="css/page.css" var="jstlCss" />
<link href="${jstlCss}" type="text/css" rel="stylesheet" media="screen" >

</head>
<body>
	<nav class="nav justify-content-center">
		<a class="nav-link" href="/servletAnnonce">retour au forum</a>
	</nav>
<div class="menu">
	<form:form method="POST" 
          action="/saveMessage" modelAttribute="me">
          	<fieldset>
          		<form:hidden path="id"/>
          		<form:label path="message">message :</form:label>
                <form:textarea class="form-control" path="message"/> <br>
                <input type="hidden" name="idu" value="${user.getId()}">
				<input type="hidden" name="ida" value="${id}">
               	<input type="submit" class="btn btn-primary" value="Submit"/>
          	</fieldset>
	</form:form>
	<c:if test="${param.validation_mes!=null}">
			<div class="alert alert-success" role="alert">
				le message vient d'être envoyé.
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				  <span aria-hidden="true">&times;</span>
				</button>
		  </div>
	</c:if>
	
	
</div>

</body>
</html>