package com.edu.hutech.major.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.hutech.major.model.Order;
import com.edu.hutech.major.model.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
	List<OrderDetail> findByOrder(Order order);
}
