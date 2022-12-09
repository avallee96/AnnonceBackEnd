package com.intiformation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intiformation.model.Commentaire;
import com.intiformation.model.Message;

public interface IMessageDao extends JpaRepository<Message, Integer>,IMessageDaoFind{

	public List<Message> findByAnnonce_id(int id);
}
