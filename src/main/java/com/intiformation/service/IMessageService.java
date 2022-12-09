package com.intiformation.service;

import java.util.List;

import com.intiformation.model.Message;

public interface IMessageService {
	
	public void ajouter(Message m);
	public void modifier(Message m);
	public void supprimer(int id);
	public Message chercherparId(int id);
	public List<Message> getall();
	public List<Message> getallannonce(int id);
	public void ajouter_message(String email_emeteur, String email_recepteur, String titre, String sujet);
}
