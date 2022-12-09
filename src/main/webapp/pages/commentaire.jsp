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
	<c:if test="${user != null}">
		<form:form method="POST" 
          action="/saveCommentaire" modelAttribute="co">
          	<fieldset>
          		<form:hidden path="id"/>
          		<form:label path="commentaire">commentaire</form:label>
                <form:textarea class="form-control" path="commentaire"/> <br>
				<input type="hidden" name="idu" value="${user.getId()}">
				<input type="hidden" name="ida" value="${id}">
               	<input type="submit" class="btn btn-primary" value="Submit"/>
          	</fieldset>
		</form:form>
	</c:if>
	

	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th colspan=7>tableau d'affichage des annonces</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>id</th>
				<th>commentaire</th>
				<th>numéro de l'annonce</th>
				<th>nom de l'utilisateur</th>
				<th>date du commentaire</th>
				<c:if test="${user != null}">
					<th>action 1</th>
					<th>action 2</th>
				</c:if>
				
	
			</tr>
			<c:forEach items="${commentaires}" var="pl">
				<tr>
					<td>${pl.getId()}</td>
					<td>${pl.getCommentaire()}</td>
					<td>${pl.getAnnonce().getId()}</td>
					<td>${pl.getUtilisateur().getUsername()}</td>
					<td>${pl.getDate_com()}</td>
					<c:if test="${user != null}">
						<c:if test="${user.getRole().getId() == 1}">
							<td><a href="/form_commentaire/${pl.getId()}/${pl.getAnnonce().getId()}" class="btn btn-primary stretched-link">modifier</a></td>
							<td><a href="/supprimer_commentaire/${pl.getId()}/${pl.getAnnonce().getId()}" class="btn btn-primary stretched-link">supprimer</a></td>
						</c:if>
						<c:if test="${user.getId() == pl.getUtilisateur().getId()}">
							<td><a href="/supprimer_commentaire/${pl.getId()}/${pl.getAnnonce().getId()}" class="btn btn-primary stretched-link">supprimer</a></td>
						</c:if>
						
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</div>
</body>
</html>