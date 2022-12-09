package com.intiformation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intiformation.dao.IUtilisateurDao;
import com.intiformation.model.Utilisateur;

@Service
public class UtilisateurService implements IUtilisateurService{

	@Autowired
	private IUtilisateurDao udao;
	
	
	public void ajouter(Utilisateur u) {
		udao.save(u);
	}
	
	public void modifier(Utilisateur u) {
		udao.save(u);
	}
	
	public void supprimer(int id) {
		udao.deleteById(id);
	}
	
	public Utilisateur chercherparId(int id) {
		Utilisateur u = udao.findById(id).get();
		return u;
	}
	
	public Utilisateur chercherparnom(String username) {
		Utilisateur u = udao.findByUsername(username);
		return u;
	}
	
	public List<Utilisateur> getall(){
		List<Utilisateur> liste = udao.findAll();
		return liste;
	}
}
