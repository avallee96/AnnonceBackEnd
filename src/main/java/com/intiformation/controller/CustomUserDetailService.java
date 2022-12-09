package com.intiformation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.intiformation.model.Role;
import com.intiformation.model.Utilisateur;
import com.intiformation.service.IUtilisateurService;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	IUtilisateurService uservice;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur user = uservice.chercherparnom(username);
		if(user==null) {
			throw new UsernameNotFoundException(username);
		}
		
		List<GrantedAuthority> authorities = getGrantedAuthority(user);
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

	}
	
	private List<GrantedAuthority> getGrantedAuthority(Utilisateur u){
		List<GrantedAuthority> liste = new ArrayList<>();
		List<Role> roles = new ArrayList<>();
		roles.add(u.getRole());
		for(Role r:roles)
		{
			liste.add(new SimpleGrantedAuthority(r.getNom()));
		}
		return liste;
	}

}
