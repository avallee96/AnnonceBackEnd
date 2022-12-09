package com.intiformation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intiformation.model.Annonce;

public interface IAnnonce extends JpaRepository<Annonce, Integer>{

	public List<Annonce> findByValid(boolean bo);
	public List<Annonce> findByCategorie_id(int id);
	public List<Annonce> findByUtilisateur_id(int id);
}
