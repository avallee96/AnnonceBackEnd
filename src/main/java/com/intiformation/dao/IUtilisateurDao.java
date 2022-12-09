package com.intiformation.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.intiformation.model.Utilisateur;

public interface IUtilisateurDao extends JpaRepository<Utilisateur, Integer>{
	
	public Utilisateur findByUsername(String user);
	public Utilisateur findByEmail(String email);

}
