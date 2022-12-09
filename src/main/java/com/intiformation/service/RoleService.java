package com.intiformation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intiformation.dao.IRoleDao;
import com.intiformation.model.Role;

@Service
public class RoleService implements IRoleService{
	
	@Autowired
	private IRoleDao rdao;
	
	public Role chercherparId(int id) {
		Role r = rdao.findById(id).get();
		return r;
	}
}
