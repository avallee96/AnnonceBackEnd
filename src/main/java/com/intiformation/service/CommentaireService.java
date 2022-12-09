package com.intiformation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intiformation.dao.ICommentaire;
import com.intiformation.model.Commentaire;

@Service
public class CommentaireService implements ICommentaireService{
	
	@Autowired
	private ICommentaire cdao;
	
	public void ajouter(Commentaire c) {
		cdao.save(c);
	}
	
	public void modifier(Commentaire c) {
		cdao.save(c);
	}
	
	public void supprimer(int id) {
		cdao.deleteById(id);
	}
	
	public Commentaire chercherparId(int id) {
		Commentaire c = cdao.findById(id).get();
		return c;
	}
	
	public List<Commentaire> getall(){
		List<Commentaire> liste = cdao.findAll();
		return liste;
	}
	
	public List<Commentaire> getallbyannonce(int id){
		List<Commentaire> liste = cdao.findByAnnonce_id(id);
		return liste;
	}

}
