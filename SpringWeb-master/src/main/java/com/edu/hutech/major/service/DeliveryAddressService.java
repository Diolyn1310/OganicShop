package com.edu.hutech.major.service;

import org.springframework.stereotype.Service;

import com.edu.hutech.major.model.DeliveryAddress;
import com.edu.hutech.major.model.Order;

@Service
public interface DeliveryAddressService {
	public void add(DeliveryAddress  deliveryAddress);
	public DeliveryAddress findByOrder(Order order);
}
