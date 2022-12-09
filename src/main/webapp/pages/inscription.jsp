<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<link href="css/page.css" type="text/css" rel="stylesheet" media="screen" >

</head>
<body>
	<nav class="nav justify-content-center">
		<a class="nav-link" href="/servletlogin">connexion</a>
		<a class="nav-link" href="/servletAnnonce">consulter les annonces</a>
	</nav>
<div class="menu">
	<form:form method="POST" 
    		action="/register" modelAttribute="us">
       		<fieldset>
				<legend>inscription</legend>
				<form:hidden path="id"/>
				<form:label path="username">login:</form:label>
				<form:input class="form-control" path="username" /><br><br>
				<form:label path="password">password:</form:label>
				<form:input class="form-control" type="password" path="password"/><br><br>
				<form:label path="email">email:</form:label>
				<form:input class="form-control" type="email" path="email"/><br><br>
				<input type="hidden" name="rol" value="2">
				<input type="hidden" name="val" value="false">
				<input type="submit" class="btn btn-primary" value="Submit">
	   		</fieldset>
		</form:form>
		<c:if test="${param.utilisateur!=null}">
			<div class="alert alert-success" role="alert">
				l'utilisateur existe déjà dans nos base de donnée
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
</div>

</body>
</html>