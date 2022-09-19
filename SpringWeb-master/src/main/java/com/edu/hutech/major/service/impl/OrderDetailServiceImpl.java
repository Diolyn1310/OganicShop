package com.edu.hutech.major.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.hutech.major.model.Order;
import com.edu.hutech.major.model.OrderDetail;
import com.edu.hutech.major.repository.OrderDetailRepository;
import com.edu.hutech.major.service.OrderDetailService;

@Component
public class OrderDetailServiceImpl implements OrderDetailService {

	
	@Autowired
	OrderDetailRepository repository;
	
	@Override
	public void add(OrderDetail orderDetail) {
		repository.save(orderDetail);
	}

	@Override
	public List<OrderDetail> findByOrder(Order order) {
		return repository.findByOrder(order);
	}

}
