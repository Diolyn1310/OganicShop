package com.edu.hutech.major.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.edu.hutech.major.global.GlobalData;
import com.edu.hutech.major.model.DeliveryAddress;
import com.edu.hutech.major.model.Order;
import com.edu.hutech.major.model.OrderDetail;
import com.edu.hutech.major.model.UserRole;
import com.edu.hutech.major.service.DeliveryAddressService;
import com.edu.hutech.major.service.OrderDetailService;
import com.edu.hutech.major.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	OrderService repoOrder;
	
	@Autowired
	DeliveryAddressService repoDelivery;
	
	@Autowired
	OrderDetailService repoOrderDetail;
	
	@Autowired
	HttpSession session;
	
	@ModelAttribute("deliveryAddress")
	public DeliveryAddress initModel() {
		return new DeliveryAddress();
	}
	
	@PostMapping("/payNow")
	public String payNow(Model model, DeliveryAddress deliveryAddress) {
		UserRole userRole = (UserRole)session.getAttribute("user");
		if(userRole != null) {
			
			double total = GlobalData.cart.stream()
					.map(e -> e.getPrice())
					.reduce(0.0, (subTotal, price) -> subTotal + price);
			// Add order
			Order order = repoOrder.add( new Order(userRole.getUser(), total));
			// Add for order detail
			GlobalData.cart.forEach( e -> {
				repoOrderDetail.add( new OrderDetail(e, order));
			} );
			// Add Delivery Address
			repoDelivery.add(new DeliveryAddress(deliveryAddress, order));
			GlobalData.cart.clear();
		}
		return "redirect:/home";
	}
	
	@GetMapping("/order")
	public String myOrder(Model model) {
		UserRole userRole = (UserRole)session.getAttribute("user");
		if(userRole != null) {
			List<Order> orders = repoOrder.findByUser(userRole.getUser());
			model.addAttribute("orders", orders);
		}
		return "my-order";
	}
	
	@GetMapping("/order/detail/{id}")
	public String viewOrderDetail(Model model, @PathVariable("id") Integer id) {
		Order order = repoOrder.findById(id);
		List<OrderDetail> orderDetails = repoOrderDetail.findByOrder(order);
		model.addAttribute("orderDetails", orderDetails);
		return "order-detail";
	}
	
	@GetMapping("/delivery/detail/{id}")
	public String viewDeliveryDetail(Model model, @PathVariable("id") Integer id) {
		Order order = repoOrder.findById(id);
		DeliveryAddress deliveryAddress = repoDelivery.findByOrder(order);
		model.addAttribute("deliveryAddress", deliveryAddress );
		return "delivery-detail";
	}
	
}
