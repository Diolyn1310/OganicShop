package com.edu.hutech.major.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.hutech.major.model.DeliveryAddress;
import com.edu.hutech.major.model.Order;
import com.edu.hutech.major.repository.DeliveryAddressRepository;
import com.edu.hutech.major.service.DeliveryAddressService;

@Component
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

	
	@Autowired
	DeliveryAddressRepository repository;
	

	@Override
	public void add(DeliveryAddress deliveryAddress) {
		repository.save(deliveryAddress);
	}


	@Override
	public DeliveryAddress findByOrder(Order order) {
		return repository.findByOrder(order);
	}

}
