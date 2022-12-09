package com.intiformation.dao;

import java.util.List;

import com.intiformation.model.Commentaire;

public interface ICommentaireDaoFind {
	
	public List<Commentaire> afficherparAnnonce(int id);

}
