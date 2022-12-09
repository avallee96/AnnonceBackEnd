package com.intiformation.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.PathParam;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.intiformation.model.Annonce;
import com.intiformation.model.AnnoncePDFExporter;
import com.intiformation.model.Categorie;
import com.intiformation.model.Commentaire;
import com.intiformation.model.Message;
import com.intiformation.model.Utilisateur;
import com.intiformation.service.IAnnonceService;
import com.intiformation.service.ICategorieService;
import com.intiformation.service.ICommentaireService;
import com.intiformation.service.IMessageService;
import com.intiformation.service.IUtilisateurService;
import com.lowagie.text.DocumentException;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class AnnonceController {

	@Autowired
	private IAnnonceService aservice;
	
	@Autowired
	private IUtilisateurService uservice;
	
	@Autowired
	private ICategorieService cservice;
	
	@Autowired
	private ICommentaireService coservice;
	
	@Autowired
	private IMessageService mservice;
	

	@GetMapping("/annonce")
	public List<Annonce> afficherA() {
		List<Annonce> annonces = aservice.non_valid(true);
		return annonces;
	}
	
	@GetMapping("/annonce/{id}")
	public Annonce afficherPE(@PathVariable("id") int id) {
		Annonce annonce = aservice.chercherparId(id);
		return annonce;
	}
	
	@GetMapping("/annonce/non_valid")
	public List<Annonce> non_valid2(){
		List<Annonce> annonces = aservice.non_valid(false);
		return annonces;
	}
	
	@GetMapping("/annonce/categorie/{id}")
	public List<Annonce> annonce_cat(@PathVariable int id){
		List<Annonce> annonces = aservice.chercherparcategorie(id);
		return annonces;
	}
	
	@GetMapping("/annonce/utilisateur/{id}")
	public List<Annonce> annonce_user(@PathVariable int id){
		List<Annonce> annonces = aservice.chercherparutilisateur(id);
		return annonces;
 	}
	
	
	/*
	@PostMapping("/annonce")
	public void ajouter(@RequestBody Annonce a) {
		aservice.ajouter(a);
	}
	*/
	
	@PostMapping("/annonce" )
	public void ajouter(@RequestParam("titre") String titre, @RequestParam("description") String description, @RequestParam(value = "photo", required = false) MultipartFile file, @RequestParam("categorie") int cat, @RequestParam("utilisateur") int user) {
		Annonce a = new Annonce();
		System.out.println(titre);
		a.setTitre(titre);
		a.setDescription(description);
		Utilisateur u = uservice.chercherparId(user);
		a.setUtilisateur(u);
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime( a.getDate_exp() );
		cal.add( GregorianCalendar.YEAR, 10 );
		a.setDate_exp(cal.getTime());
		if(file!=null) {
			String filename = file.getOriginalFilename();
		
		try {
			byte barr[]=file.getBytes();
			BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream("src/main/resources/static/image/"+filename));
			a.setPhoto("image/"+filename);
			bout.write(barr);
			bout.flush();
			bout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		
		
		Categorie c = cservice.chercherparId(cat);
		a.setCategorie(c);
		aservice.ajouter(a);
	}
	
	@PutMapping("/annonce/validation")
	public void valid(@RequestParam("id") int id) {
		Annonce a = aservice.chercherparId(id);
		a.setValid(true);
		aservice.modifier(a);
		
	}
	
	@PutMapping("/annonce")
	public void afficherC(@RequestParam("id") int id, @RequestParam("titre") String titre, @RequestParam("description") String description, @RequestParam(value = "photo", required = false) MultipartFile file, @RequestParam("categorie") int cat, @RequestParam("utilisateur") int user) {
			Annonce a = null;
		
		if(id == 0) {
			a = new Annonce();
			Calendar cal = GregorianCalendar.getInstance();
			cal.setTime( a.getDate_exp() );
			cal.add( GregorianCalendar.YEAR, 10 );
			a.setDate_exp(cal.getTime());
		} else {
			a = aservice.chercherparId(id);
			a.setDate_pub(new Date());
			a.setDate_exp(new Date());
			Calendar cal = GregorianCalendar.getInstance();
			cal.setTime( a.getDate_exp() );
			cal.add( GregorianCalendar.YEAR, 10 );
			a.setDate_exp(cal.getTime());
		}
		 
		
		
		if(titre != null) {
			a.setTitre(titre);
		}
		if(description != null) {
			a.setDescription(description);
		}
		if(file != null) {
			String filename = file.getOriginalFilename();
			String f = "image/" + filename;
			
			/* -----------------------------------------------------------------------------------------------------
			 * la condition permet de vérifier si une image existe et si elle existe alors on la supprime du dossier
			 * -----------------------------------------------------------------------------------------------------*/
			
			if(f.equals(a.getPhoto()) == false) {
				try {
					FileUtils.touch(new File("src/main/resources/static/"+a.getPhoto()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				File fileToDelete =  FileUtils.getFile("src/main/resources/static/"+a.getPhoto());
			    boolean success = FileUtils.deleteQuietly(fileToDelete);
			}
			
			try {
				byte barr[]=file.getBytes();
				BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream("src/main/resources/static/image/"+filename));
				a.setPhoto("image/"+filename);
				bout.write(barr);
				bout.flush();
				bout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(cat != 0) {
			Categorie c = cservice.chercherparId(cat);
			a.setCategorie(c);
		}
		
		if(user !=0) {
			Utilisateur u = uservice.chercherparId(user);
			a.setUtilisateur(u);
		}
		aservice.modifier(a);
	}
	
	
	@DeleteMapping("/annonce/{id}")
	public void afficherN(@PathVariable("id") int id) {
		Annonce a = aservice.chercherparId(id);
		List<Commentaire> c = a.getCommentaires();
		for(int i=0;i<c.size();i++) {
			int j = c.get(i).getId();
			c.get(i).setAnnonce(null);
			coservice.supprimer(j);
		}
		List<Message> m = a.getMessages();
		for(int i=0;i<m.size();i++) {
			int j = m.get(i).getId();
			m.get(i).setAnnonce(null);
			mservice.supprimer(j);
		}
		try {
			FileUtils.touch(new File("src/main/resources/static/"+a.getPhoto()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File fileToDelete =  FileUtils.getFile("src/main/resources/static/"+a.getPhoto());
	    boolean success = FileUtils.deleteQuietly(fileToDelete);
		aservice.supprimer(id);
	}
	
	@GetMapping("/export")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		
		String headerkey = "Content-Disposition";
		String headerValue = "attachement; filename=annonce.pdf";
		
		response.setHeader(headerkey, headerValue);
		
		List<Annonce> annonces = aservice.getall();
		
		AnnoncePDFExporter exporter = new AnnoncePDFExporter(annonces);
		exporter.export(response);
	}
	
	
	
}