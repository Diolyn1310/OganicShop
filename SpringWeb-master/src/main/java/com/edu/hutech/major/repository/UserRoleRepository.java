package com.edu.hutech.major.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.hutech.major.model.User;
import com.edu.hutech.major.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	public Optional<UserRole> findByUser(User user);
}
