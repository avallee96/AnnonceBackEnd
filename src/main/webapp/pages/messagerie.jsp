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
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th colspan=7>tableau d'affichage des messages</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>id</th>
				<th>message</th>
				<th>numéro de l'annonce</th>
				<th>nom de l'utilisateur</th>
				<th>date du message</th>
				<th>action 1</th>
				<th>action 2</th>
	
			</tr>
			<c:forEach items="${messages}" var="pl">
				<c:forEach items="${pl.getUtilisateur()}" var="plt">
					<c:if test="${plt.getId() == user.getId()}">
						<tr>
							<td>${pl.getId()}</td>
							<td>${pl.getMessage()}</td>
							<td>${pl.getAnnonce().getId()}</td>
							<td>
								<c:forEach items="${pl.getUtilisateur()}" var="me">
									<c:if test="${pl.getId() != user.getId()}">
										<c:set var="id" value="${pl.getId()}" />
									</c:if>
									${me.getUsername()}
								</c:forEach>
							</td>
							<td>${pl.getDate_mes()}</td>
							<td><a href="/message_annonce/${pl.getAnnonce().getId()}/id}" class="btn btn-primary stretched-link">répondre</a></td>
							<td><a href="/supprimer_message/${pl.getId()}/${pl.getAnnonce().getId()}" class="btn btn-primary stretched-link">supprimer</a></td>
						</tr>
					</c:if>
				</c:forEach>
				
					
				
			</c:forEach>
		</tbody>
	</table>

	
	
</div>

</body>
</html>