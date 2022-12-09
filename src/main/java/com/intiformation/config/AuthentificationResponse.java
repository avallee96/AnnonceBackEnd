package com.intiformation.config;

import org.springframework.web.bind.annotation.RequestMapping;

public class AuthentificationResponse {
	
	private String jwt;

	public String getJwt() {
		return jwt;
	}

	public AuthentificationResponse(String jwt) {
		this.jwt = jwt;
	}

	public AuthentificationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	

}
