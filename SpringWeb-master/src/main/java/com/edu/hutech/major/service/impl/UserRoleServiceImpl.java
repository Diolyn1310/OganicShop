package com.edu.hutech.major.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.hutech.major.model.User;
import com.edu.hutech.major.model.UserRole;
import com.edu.hutech.major.repository.UserRoleRepository;
import com.edu.hutech.major.service.UserRoleService;

@Component
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleRepository repository;
	
	@Override
	public UserRole add(UserRole userRole) {
		return repository.save(userRole);
	}

	@Override
	public Optional<UserRole> findByUser(User user) {		
		return repository.findByUser(user);
	}

}
