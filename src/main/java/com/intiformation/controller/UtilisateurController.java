package com.intiformation.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.intiformation.dao.IRoleDao;
import com.intiformation.model.Annonce;
import com.intiformation.model.AnnoncePDFExporter;
import com.intiformation.model.Categorie;
import com.intiformation.model.Commentaire;
import com.intiformation.model.Role;
import com.intiformation.model.Utilisateur;
import com.intiformation.model.UtilisateurPDFExporter;
import com.intiformation.service.IAnnonceService;
import com.intiformation.service.ICategorieService;
import com.intiformation.service.ICommentaireService;
import com.intiformation.service.IMessageService;
import com.intiformation.service.IRoleService;
import com.intiformation.service.IUtilisateurService;
import com.lowagie.text.DocumentException;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class UtilisateurController {

	@Autowired
	private IUtilisateurService uservice;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	
	@GetMapping("/utilisateur")
	public List<Utilisateur> afficherA() {
		List<Utilisateur> utilisateurs = uservice.getall();
		return utilisateurs;
	}
	
	@PostMapping("/utilisateur")
	public void ajouterA(@RequestBody Utilisateur u) {
		u.setPassword(encoder.encode(u.getPassword()));
		uservice.ajouter(u);
	}
	
	@GetMapping("/utilisateur/{id}")
	public Utilisateur afficherL(@PathVariable("id") int id) {
		Utilisateur u = uservice.chercherparId(id);
		return u;
	}
	
	@GetMapping("/utilisateur/username/{username}")
	public Utilisateur username(@PathVariable("username") String username) {
		System.out.println("test");
		Utilisateur u = uservice.chercherparnom(username);
		return u;
	}
	
	@PutMapping("/utilisateur")
	public void affiche(@RequestBody Utilisateur u) {
		uservice.modifier(u);
	}
	
	@DeleteMapping("/utilisateur/{id}")
	public void acces_non(@PathVariable("id") int id) {
		uservice.supprimer(id);
	}
	
	
}
