package com.edu.hutech.major.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.hutech.major.model.Order;
import com.edu.hutech.major.model.OrderDetail;

@Service
public interface OrderDetailService {
	public void add(OrderDetail orderDetail );
	public List<OrderDetail> findByOrder(Order order); 
}
