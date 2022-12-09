package com.intiformation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intiformation.dao.ICategorie;
import com.intiformation.model.Categorie;

@Service
public class CategorieService implements ICategorieService{
	
	@Autowired
	private ICategorie cdao;
	
	public void ajouter(Categorie c) {
		cdao.save(c);
	}
	
	public void modifier(Categorie c) {
		cdao.save(c);
	}
	
	public void supprimer(int id) {
		cdao.deleteById(id);
	}
	
	public Categorie chercherparId(int id) {
		Categorie c = cdao.findById(id).get();
		return c;
	}
	
	public List<Categorie> getall(){
		List<Categorie> liste = cdao.findAll();
		return liste;
	}
	
	

}
