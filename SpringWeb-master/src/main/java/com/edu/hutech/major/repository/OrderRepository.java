package com.edu.hutech.major.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.hutech.major.model.Order;
import com.edu.hutech.major.model.User;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Integer>  {
	public List<Order> findByUser(User user);
	
	public Order findById(int id);
}
