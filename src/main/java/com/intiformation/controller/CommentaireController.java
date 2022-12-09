package com.intiformation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intiformation.model.Annonce;
import com.intiformation.model.Commentaire;
import com.intiformation.model.Role;
import com.intiformation.model.Utilisateur;
import com.intiformation.service.IAnnonceService;
import com.intiformation.service.ICommentaireService;
import com.intiformation.service.IUtilisateurService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class CommentaireController {
	
	@Autowired
	private ICommentaireService cservice;
	
	
	@GetMapping("/commentaire")
	public List<Commentaire> afficherA() {
		List<Commentaire> commentaires = cservice.getall();
		return commentaires;
	}
	
	@PostMapping("/commentaire")
	public void ajouterA(@RequestBody Commentaire c) {
		cservice.ajouter(c);
	}
	
	@GetMapping("/commentaire/{id}")
	public Commentaire afficherForm(@PathVariable("id") int id) {
		Commentaire commentaire = cservice.chercherparId(id);
		return commentaire;
	}
	
	@GetMapping("/commentaire/annonce/{id}")
	public List<Commentaire> commentaire_annonce(@PathVariable("id") int id) {
		List<Commentaire> commentaire = cservice.getallbyannonce(id);
		return commentaire;
	}
	
	@DeleteMapping("/commentaire/{id}")
	public void supprimerP(@PathVariable("id") int id) {
		cservice.supprimer(id);
	}
	
	@PutMapping("/commentaire")
	public void modifier(@RequestBody Commentaire c) {
		cservice.modifier(c);
	}
}
