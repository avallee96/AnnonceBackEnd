package com.intiformation.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.intiformation.model.Commentaire;

@Repository
@Transactional
public class ICommentaireImpl implements ICommentaireDaoFind{

	@PersistenceContext
	EntityManager em;
	
	public List<Commentaire> afficherparAnnonce(int id)
	{
		
		Query q=em.createQuery(" select c from Commentaire c where c.id_annonce=:id");
		q.setParameter("id", id);
		List<Commentaire> liste=q.getResultList();
		em.close();
		return liste;
	}
}
