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
	<h1>bienvenue sur l'application Gestion des utilisateur</h1>
	
	<c:if test="${param.export!=null}">
		<div class="alert alert-success" role="alert">
			vous avez exportez les fichiers en pdf
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:if>
	
	<form:form method="POST" 
          action="/saveUser" modelAttribute="us">
          	<fieldset>
				<legend>inscription</legend>
				<form:hidden path="id"/>
				<form:label path="username">login:</form:label>
				<form:input class="form-control" path="username" /><br><br>
				<form:label path="password">password:</form:label>
				<form:input class="form-control" type="password" path="password"/><br><br>
				<form:label path="email">email:</form:label>
				<form:input class="form-control" type="email" path="email"/><br><br>
				<select class="custom-select" name="val">
					<option value="true">bloquer</option>
					<option value="false">libre</option>
				</select> <br>
				<select class="custom-select" name="rol">
					<option value="1">admin</option>
					<option value="2">utilisateur</option>
				</select> <br>
				<input type="submit" class="btn btn-primary" value="Submit">
          	</fieldset>
	</form:form>

	<form action="/exportutilisateur">
		<input type="submit" class="btn btn-primary" value="exporter les données en pdf"/>
	</form>

	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th colspan=8>tableau d'affichage des annonces</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>id</th>
				<th>username</th>
				<th>password</th>
				<th>email</th>
				<th>valid</th>
				<th>role</th>
				<th>action 1</th>
				<th>action 2</th>
	
			</tr>
			<c:forEach items="${utilisateurs}" var="pl">
				<tr>
					<td>${pl.getId()}</td>
					<td>${pl.getUsername()}</td>
					<td>${pl.getPassword()}</td>
					<td>${pl.getEmail()}</td>
					<td>${pl.isValid()}</td>
					<td>${pl.getRole().getNom()}</td>
					<td><a href="/form_utilisateur/${pl.getId()}" class="btn btn-primary stretched-link">modifier</a></td>
					<td><a href="/supprimer_utilisateur/${pl.getId()}" class="btn btn-primary stretched-link">supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</div>
</body>
</html>