package com.edu.hutech.major.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Order_Details")
public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="Price")
	private double price; 
	
	@Column(name="Weight")
	private String weight; 
	
	@ManyToOne
	@JoinColumn(name = "Product_Id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "Order_Id")
	private Order order; 
	
	public OrderDetail(Product product, Order order) {
		this.order = order;
		this.product = product;
		this.weight = "1KG";
		this.price = product.getPrice();
	}
	
}
