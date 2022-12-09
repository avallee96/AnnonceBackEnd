package com.intiformation.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.intiformation.model.Message;

@Repository
@Transactional
public class IMessageDaoImpl{

	@PersistenceContext
	EntityManager em;
	
	public List<Message> selectbyAnnonce_message(int id){
		Query q=em.createQuery("from Message where id_annonce="+ id);
		List<Message> liste=q.getResultList();
		em.close();
		return liste;
	}
	
}
