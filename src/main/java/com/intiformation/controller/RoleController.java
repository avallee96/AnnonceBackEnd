package com.intiformation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intiformation.model.Role;
import com.intiformation.service.IRoleService;


@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class RoleController {
	
	@Autowired
	private IRoleService rservice;
	
	@GetMapping("/role/{id}")
	public Role afficherL(@PathVariable("id") int id) {
		Role r = rservice.chercherparId(id);
		return r;
	}

}
