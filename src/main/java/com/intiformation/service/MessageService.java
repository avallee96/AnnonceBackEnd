package com.intiformation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import com.intiformation.dao.IMessageDao;
import com.intiformation.model.Message;
import com.intiformation.model.Utilisateur;

@Service
public class MessageService implements IMessageService{

	@Autowired
	private IMessageDao mdao;
	
	@Autowired
	IUtilisateurService utilisateurService;
	
	@Autowired
    private JavaMailSender emailSender;
	
	
	public void ajouter(Message m) {
		mdao.save(m);
	}
	
	public void modifier(Message m) {
		mdao.save(m);
	}
	
	public void supprimer(int id) {
		List<Utilisateur> utilisateurs=utilisateurService.getall();
		List<Message> message = new ArrayList<>();
		for(Utilisateur u:utilisateurs)
		{
			for(Message m :u.getMessage())
			{
				if(m.getId()!=id)
				{
					message.add(m);
				}
			}
			u.setMessage(message);
		}
		mdao.deleteById(id);
	}
	
	public Message chercherparId(int id) {
		Message m = mdao.findById(id).get();
		return m;
	}
	
	public List<Message> getall(){
		List<Message> liste = mdao.findAll();
		return liste;
	}
	
	public List<Message> getallannonce(int id){
		List<Message> liste = mdao.selectbyAnnonce_message(id);
		return liste;
	}
	
	public void ajouter_message(String email_emeteur, String email_recepteur, String titre, String sujet) {
		 SimpleMailMessage message = new SimpleMailMessage(); 
		 message.setFrom(email_emeteur);
		 message.setTo(email_recepteur); 
	     message.setSubject(titre); 
	     message.setText(sujet);
	     emailSender.send(message);
	}
}
