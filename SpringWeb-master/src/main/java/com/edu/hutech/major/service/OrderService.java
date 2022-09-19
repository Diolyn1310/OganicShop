package com.edu.hutech.major.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.hutech.major.model.Order;
import com.edu.hutech.major.model.User;

@Service
public interface OrderService {

	public Order add(Order order);
	
	public List<Order> findByUser(User user);
	
	public Order findById(int id);
	
	public List<Order> findByAll();
	
	
}
