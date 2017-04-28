package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product implements java.io.Serializable {

	 
	private static final long serialVersionUID = 1L;
	private Integer code;
	private String description;
	private Double price;
	private Integer quantity;
//	private Set<OrderLines> orderLineses = new HashSet<OrderLines>(0);

	public Product() {
	}

	public Product(Integer code, String description, Double price, Integer quantity) {
		this.code = code;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

	
	@Id
	@Column(name = "code", unique = true, nullable = false)
	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "price", precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "products")
//	public Set<OrderLines> getOrderLineses() {
//		return this.orderLineses;
//	}
//
//	public void setOrderLineses(Set<OrderLines> orderLineses) {
//		this.orderLineses = orderLineses;
//	}

}
