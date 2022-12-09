package com.intiformation.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.intiformation.model.Utilisateur;
import com.intiformation.service.IUtilisateurService;

@Component
public class AuthentificationSucessHandlerImpl implements AuthenticationSuccessHandler {

	@Autowired
	IUtilisateurService userService;
	
	@Autowired
	HttpSession session;
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String roleUser="";
		Object user= authentication.getPrincipal();
		String username=((UserDetails)user).getUsername();
		
		Utilisateur u=userService.chercherparnom(username);
		session.setAttribute("user", u);
		if(u.isValid()==false) {
			response.sendRedirect("/servletAnnonce");
		} else {
			response.sendRedirect("/acces_non_auto");
		}
		
		
		/*
		Collection<? extends GrantedAuthority> roles= authentication.getAuthorities();
		for(GrantedAuthority ga:roles)
		{
			roleUser=ga.getAuthority();
		}
		System.out.println(roleUser);
		
		if(roleUser.equals("ad"))
		{
			response.sendRedirect("/admin/afficherBonjour");
		}
		else if(roleUser.equals("ut"))
		{
			response.sendRedirect("/utilisateur/afficherBonjour");
		}*/
	}

}
