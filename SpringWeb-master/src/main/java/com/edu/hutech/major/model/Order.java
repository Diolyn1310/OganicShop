package com.edu.hutech.major.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "Status")
	private boolean status;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Create_Date")
	private Date createDate;
	
	@Column(name = "Total")
	private double total;
	
	/* Dùng cho trường hợp hủy hóa đơn */
	@Column(name = "Available")
	private boolean available;
	
	@ManyToOne
	@JoinColumn(name = "User_Id")
	private User user;

	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails;
	
	@OneToOne(mappedBy = "order")
	private DeliveryAddress deliveryAddress;
	
	public Order(User user, double total) {
		this.user = user;
		this.status = false;
		this.total = total;
		this.available = true;
		this.createDate = new java.util.Date();		
	}
}
