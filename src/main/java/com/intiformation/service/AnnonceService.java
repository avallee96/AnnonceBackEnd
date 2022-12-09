package com.intiformation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intiformation.dao.IAnnonce;
import com.intiformation.model.Annonce;
import com.intiformation.model.Commentaire;
import com.intiformation.model.Message;
import com.intiformation.model.Utilisateur;

@Service
public class AnnonceService implements IAnnonceService{

	@Autowired
	private IAnnonce adao;
	
	@Autowired
	ICommentaireService cservice;
	
	public void ajouter(Annonce a) {
		adao.save(a);
	}
	
	public void modifier(Annonce a) {
		adao.save(a);
	}
	
	public void supprimer(int id) {
		adao.deleteById(id);
	}
	
	public Annonce chercherparId(int id) {
		Annonce a = adao.findById(id).get();
		return a;
	}
	
	public List<Annonce> chercherparcategorie(int id) {
		List<Annonce> a = adao.findByCategorie_id(id);
		return a;
	}
	
	public List<Annonce> chercherparutilisateur(int id) {
		List<Annonce> a = adao.findByUtilisateur_id(id);
		return a;
	}
	
	public List<Annonce> getall(){
		List<Annonce> liste = adao.findAll();
		return liste;
	}
	
	public List<Annonce> non_valid(boolean b){
		List<Annonce> liste = adao.findByValid(b);
		return liste;
	}
}
