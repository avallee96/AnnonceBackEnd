package com.intiformation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intiformation.model.Commentaire;

public interface ICommentaire extends JpaRepository<Commentaire, Integer>{
	
	
	public List<Commentaire> findByAnnonce_id(int id);

}
