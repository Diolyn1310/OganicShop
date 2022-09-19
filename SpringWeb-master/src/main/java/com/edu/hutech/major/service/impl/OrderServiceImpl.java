package com.edu.hutech.major.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.hutech.major.model.Order;
import com.edu.hutech.major.model.User;
import com.edu.hutech.major.repository.OrderRepository;
import com.edu.hutech.major.service.OrderService;

@Component
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository repository;
	
	@Override
	public Order add(Order order) {
		return repository.save(order);
	}

	@Override
	public List<Order> findByUser(User user) {
		return repository.findByUser(user);
	}

	@Override
	public Order findById(int id) {
		return repository.findById(id);
	}

	@Override
	public List<Order> findByAll() {
		return repository.findAll();
	}

}
