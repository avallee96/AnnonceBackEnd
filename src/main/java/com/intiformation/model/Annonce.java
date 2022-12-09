package com.intiformation.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Annonce {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titre;
	private String description;
	private boolean valid;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date date_pub = new Date();
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date date_exp = new Date();
	private String photo;
	
	@ManyToOne
	@JoinColumn(name= "id_categorie")
	private Categorie categorie;
	
	@ManyToOne
	@JoinColumn(name= "id_utilisateur")
	private Utilisateur utilisateur;
	
	@OneToMany(mappedBy = "annonce")
	@JsonIgnore
	private List<Commentaire> commentaires;
	
	@OneToMany(mappedBy = "annonce")
	@JsonIgnore
	private List<Message> messages;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate_pub() {
		return date_pub;
	}

	public void setDate_pub(Date date_pub) {
		this.date_pub = date_pub;
	}

	public Date getDate_exp() {
		return date_exp;
	}

	public void setDate_exp(Date date_exp) {
		this.date_exp = date_exp;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Annonce(String titre, String description, boolean valid, Date date_pub, Date date_exp, String photo,
			Categorie categorie, Utilisateur utilisateur) {
		super();
		this.titre = titre;
		this.description = description;
		this.valid = valid;
		this.date_pub = date_pub;
		this.date_exp = date_exp;
		this.photo = photo;
		this.categorie = categorie;
		this.utilisateur = utilisateur;
	}

	public Annonce() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
