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
		<c:if test="${user == null}">
		<a href="/servletLogin" class="nav-link" >connexion</a>
		<a href="/servletRegister" class="nav-link" >inscription</a>
	</c:if>
	<c:if test="${user != null}">
		<a href="/Logout" class="nav-link" >deconnexion</a>
		<c:if test="${user.getRole().getId() == 1}">
			<a href="/servletutilisateur" class="nav-link" >gestion des utilisateurs</a>
			<a href="/servletcategorie" class="nav-link" >gestion des catégories</a>
			<a href="/servletannonce_invalide" class="nav-link" >voir les annonces à valider</a>
		</c:if>
		<a href="/servletperso/${user.getId()}" class="nav-link" >espace personnel</a>
		<!--<a href="/servletmessage/${user.getId()}" class="nav-link" >messagerie</a>-->
	</c:if>
	</nav>
<div class="menu">
	<h1>bienvenue sur l'application Gestion des annonces ${user.getUsername()}</h1>

	<c:if test="${param.validation!=null}">
		<div class="alert alert-success" role="alert">
			vous venez de valider une annonce
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:if>

	<c:if test="${param.annonce!=null}">
		<div class="alert alert-success" role="alert">
			votre annonce est en attente de confirmation.
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:if>

	<c:if test="${param.export!=null}">
		<div class="alert alert-success" role="alert">
			vous avez exportez les fichiers en pdf
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:if>

	<c:if test="${user != null}">
		<form:form method="POST" 
          action="/saveAnnonce" enctype="multipart/form-data" modelAttribute="an">
          	<fieldset>
				<legend>ajout d'une annonce</legend>
          		<form:hidden path="id"/>
				<form:hidden path="photo"/>
          		<form:label path="titre">titre</form:label>
                <form:input class="form-control" path="titre"/> <br>
                <form:label path="description">description</form:label>
                <form:textarea class="form-control form-desc" path="description"/> <br>
                <select class="custom-select" name="cate">
					<c:forEach items="${categories}" var="ca">
						<option value="${ca.getId()}">${ca.getNom()}</option>
					</c:forEach>
				</select> <br>
				<label for="pname">photo</label>
                <input type="file" class="btn btn-primary" id="pname" name="pho"/> <br>
               	<input type="hidden" name="idu" value="${user.getId()}">
               	<input type="submit" class="btn btn-primary" value="Submit"/>
          	</fieldset>
		</form:form>
	</c:if>
	
	
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

	<form action="/export">
		<input type="submit" class="btn btn-primary" value="exporter les données en pdf"/>
	</form>

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
				<th>catégorie</th>
				<th>utilisateur</th>
				<th>commentaire</th>
				<th>message</th>
				<th>action 1</th>
				<th>action 2</th>
				
	
			</tr>
			<c:forEach items="${annonces}" var="pl">
				<tr>
					<td>${pl.getId()}</td>
					<td>${pl.getTitre()}</td>
					<td>${pl.getDescription()}</td>
					<c:choose>
					<c:when test="${pl.getPhoto() == null}">
						<td>
							pas d'image
						</td>
					</c:when>
					<c:when test="${pl.getPhoto() != null}">
						<td>
							<button type="button" class="but-img btn btn-primary " data-toggle="modal" data-target="#/${pl.getPhoto()}">
 								 <img class="img-thumbnail" src="${pl.getPhoto()}"></img>
							</button>
							
							<div class="modal fade" id="/${pl.getPhoto()}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  								<div class="modal-dialog modal-dialog-centered" role="document">
    								<div class="modal-content">
      									<div class="modal-header">
        									<h5 class="modal-title" id="exampleModalCenterTitle">${pl.getPhoto()}</h5>
        									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          										<span aria-hidden="true">&times;</span>
        									</button>
      									</div>
      									<div class="modal-body">
      										<img  src="${pl.getPhoto()}"></img>
      									</div>
    								</div>
  								</div>
							</div>
						</td>
					</c:when>
				</c:choose>
					<td>${pl.getDate_pub()}</td>
					<td>${pl.getDate_exp()}</td>
					<td>${pl.getCategorie().getNom()}</td>
					<td>${pl.getUtilisateur().getUsername()}</td>
					<td><a href="/commentaire_annonce/${pl.getId()}" class="btn btn-primary stretched-link">voir les commentaires</a></td>
					<td><a href="/message_annonce/${pl.getId()}" class="btn btn-primary stretched-link">envoyer un message</a></td>
					<c:if test="${user.getRole().getId() == 1 || user.getId() == pl.getUtilisateur().getId()}">
						<td><a href="/supprimer_annonce/${pl.getId()}" class="btn btn-primary stretched-link">supprimer</a></td>
						<td><a href="/form_annonce/${pl.getId()}" class="btn btn-primary stretched-link">modifier</a></td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</div>

</body>
</html>