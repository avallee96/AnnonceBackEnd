package com.intiformation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intiformation.model.Role;

public interface IRoleDao extends JpaRepository<Role, Integer>{

}
