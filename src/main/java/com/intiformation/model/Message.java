package com.intiformation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String message;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date date_mes = new Date();
	
	@ManyToMany(mappedBy = "message")
	@JsonIgnore
	private List<Utilisateur> utilisateur = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name= "id_annonce")
	private Annonce annonce;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate_mes() {
		return date_mes;
	}

	public void setDate_mes(Date date_mes) {
		this.date_mes = date_mes;
	}

	public List<Utilisateur> getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(List<Utilisateur> utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Annonce getAnnonce() {
		return annonce;
	}

	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}
	
	

}
