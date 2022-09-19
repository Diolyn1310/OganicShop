package com.edu.hutech.major.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Delivery_Address")
public class DeliveryAddress implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name  = "Fullname")
	private String fullname;
	
	@Column(name  = "email")
	private String email;
	
	@Column(name  = "phone")
	private String phone;
	
	@Column(name  = " address")
	private String address;
	
	@OneToOne
	@JoinColumn(name = "Order_Id")
	private Order order;
	
	public DeliveryAddress(DeliveryAddress deliveryAddress, Order order) {
		this.order = order;
		this.fullname = deliveryAddress.getFullname();
		this.email = deliveryAddress.getEmail();
		this.address = deliveryAddress.getAddress();
		this.phone = deliveryAddress.getPhone();
	}
}
