package com.intiformation.service;

import java.util.List;

import com.intiformation.model.Utilisateur;

public interface IUtilisateurService {

	public void ajouter(Utilisateur u);
	public void modifier(Utilisateur u);
	public void supprimer(int id);
	public Utilisateur chercherparId(int id);
	public Utilisateur chercherparnom(String username);
	public List<Utilisateur> getall();
}
