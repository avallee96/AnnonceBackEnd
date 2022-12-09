package com.intiformation.controller;

import java.util.ArrayList;
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
import com.intiformation.model.Message;
import com.intiformation.model.Utilisateur;
import com.intiformation.service.IAnnonceService;
import com.intiformation.service.IMessageService;
import com.intiformation.service.IUtilisateurService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class MessageController {

	@Autowired
	private IMessageService mservice;
	
	@Autowired
	private IUtilisateurService uservice;

	
	/*
	@GetMapping("/message")
	public List<Message> afficherA() {
		List<Message> messages = mservice.getall();
		return messages;
	}*/
	
	@GetMapping("/message/{id}")
	public Message afficherB(@PathVariable("id") int id) {
		Message message = mservice.chercherparId(id);
		return message;
	}
	
	@GetMapping("/message/annonce/{id}")
	public List<Message> message_annonce(@PathVariable("id") int id) {
		List<Message> messages = mservice.getallannonce(id);
		return messages;
	}
	/*
	@PostMapping("/message")
	public void sauvegarder(@RequestBody Message m) {
		mservice.ajouter(m);
	}*/
	
	@PostMapping("/message")
	public void email(@RequestParam("message") String message, @RequestParam("id_e") int id_e, @RequestParam("id_r") int id_r, @RequestParam("titre") String titre) {
		Utilisateur u_e = uservice.chercherparId(id_e);
		Utilisateur u_r = uservice.chercherparId(id_r);
		mservice.ajouter_message(u_e.getEmail(), u_r.getEmail(), titre, message);
	}
	
	@PostMapping("/message/info")
	public void info(@RequestParam("message") String message, @RequestParam("email_e") String email_e, @RequestParam("id_r") int id_r, @RequestParam("titre") String titre) {
		Utilisateur u_r = uservice.chercherparId(id_r);
		mservice.ajouter_message(email_e, u_r.getEmail(), titre, message);
	}
	
	@DeleteMapping("/message/{id}")
	public void supprimer(@PathVariable("id") int id) {
		mservice.supprimer(id);
	}
	
	@PutMapping("/message")
	public void modifier(@RequestBody Message m) {
		mservice.modifier(m);
	}
	
}
