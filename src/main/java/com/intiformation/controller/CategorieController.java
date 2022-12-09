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
import org.springframework.web.bind.annotation.RestController;

import com.intiformation.model.Annonce;
import com.intiformation.model.Categorie;
import com.intiformation.service.ICategorieService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class CategorieController {

	@Autowired
	private ICategorieService cservice;
	
	@GetMapping("/categorie")
	public List<Categorie> afficherA() {
		List<Categorie> categories = cservice.getall();
		return categories;
	}
	
	@PostMapping("/categorie")
	public void ajouterA(@RequestBody Categorie c) {
		cservice.ajouter(c);
	}
	
	
	@GetMapping("/categorie/{id}")
	public Categorie afficherForm(@PathVariable("id") int id) {
		Categorie categorie = cservice.chercherparId(id);
		return categorie;
	}
	
	@DeleteMapping("/categorie/{id}")
	public void supprimerP(@PathVariable("id") int id) {
		cservice.supprimer(id);
	}
	
	@PutMapping("/categorie")
	public void modifier(@RequestBody Categorie c) {
		cservice.modifier(c);
	}
	
	
}
