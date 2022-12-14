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
<link href="css/page.css" type="text/css" rel="stylesheet" media="screen" >

</head>
<body>
	<nav class="nav justify-content-center">
		<a class="nav-link" href="/servletAnnonce">retour au forum</a>
	</nav>
<div class="menu">
	<form:form method="POST" 
			action="/chercheparcat">
				<select class="custom-select" name="cate2">
					<c:forEach items="${categories}" var="cat">
						<option value="${cat.getId()}">${cat.getNom()}</option>
					</c:forEach>
				</select>
				<input type="submit" class="btn btn-primary" value="Submit"/>
	</form:form>
		
	<form:form method="POST" 
			action="/chercheparuser">
				<input type="text" class="form-control" name="username" placeholder="nom de l'utilisateur"/>
				<input type="submit" class="btn btn-primary" value="Submit"/>
	</form:form>

	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th colspan=12>tableau d'affichage des annonces</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>id</th>
				<th>titre</th>
				<th>description</th>
				<th>photo</th>
				<th>date de publication</th>
				<th>date d'expiration</th>
				<th>cat??gorie</th>
				<th>utilisateur</th>
				<th>validation</th>
				
	
			</tr>
			<c:forEach items="${annonces}" var="pl">
				<tr>
					<td>${pl.getId()}</td>
					<td>${pl.getTitre()}</td>
					<td>${pl.getDescription()}</td>
					<td>${pl.getPhoto()}</td>
					<td>${pl.getDate_pub()}</td>
					<td>${pl.getDate_exp()}</td>
					<td>${pl.getCategorie().getNom()}</td>
					<td>${pl.getUtilisateur().getUsername()}</td>
					<td><a href="/validation_annonce/${pl.getId()}" class="btn btn-primary stretched-link">valider</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	

</body>
</html>