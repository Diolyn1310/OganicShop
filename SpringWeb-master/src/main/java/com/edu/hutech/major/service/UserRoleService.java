package com.edu.hutech.major.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edu.hutech.major.model.User;
import com.edu.hutech.major.model.UserRole;

@Service
public interface UserRoleService {
	 UserRole add(UserRole userRole);
	
	Optional<UserRole> findByUser(User user);
}
