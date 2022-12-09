package com.intiformation.service;

import java.util.List;

import com.intiformation.model.Annonce;

public interface IAnnonceService {

	public void ajouter(Annonce a);
	public void modifier(Annonce a);
	public void supprimer(int id);
	public Annonce chercherparId(int id);
	public List<Annonce> getall();
	public List<Annonce> non_valid(boolean b);
	public List<Annonce> chercherparcategorie(int id);
	public List<Annonce> chercherparutilisateur(int id);
}
