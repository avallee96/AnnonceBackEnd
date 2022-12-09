package com.intiformation.service;

import java.util.List;

import com.intiformation.model.Categorie;

public interface ICategorieService {
	
	public void ajouter(Categorie c);
	public void modifier(Categorie c);
	public void supprimer(int id);
	public Categorie chercherparId(int id);
	public List<Categorie> getall();
}
