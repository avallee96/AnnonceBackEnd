package com.intiformation.service;

import java.util.List;

import com.intiformation.model.Commentaire;

public interface ICommentaireService {

	public void ajouter(Commentaire c);
	public void modifier(Commentaire c);
	public void supprimer(int id);
	public Commentaire chercherparId(int id);
	public List<Commentaire> getall();
	public List<Commentaire> getallbyannonce(int id);
}
