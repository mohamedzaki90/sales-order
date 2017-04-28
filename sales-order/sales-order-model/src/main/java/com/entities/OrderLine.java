package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "order_lines")
public class OrderLine implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private int code;
  private Product product;
  private Integer quantity;

  public OrderLine() {
  }

  public OrderLine(int code) {
    this.code = code;
  }

  public OrderLine(Product products, Integer quantity) {
    this.product = products;
    this.quantity = quantity;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "code", unique = true, nullable = false)
  public int getCode() {
    return this.code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @Cascade(CascadeType.SAVE_UPDATE)
  @JoinColumn(name = "product")
  public Product getProduct() {
    return this.product;
  }

  public void setProduct(Product products) {
    this.product = products;
  }

  @Column(name = "quantity")
  public Integer getQuantity() {
    return this.quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

}
