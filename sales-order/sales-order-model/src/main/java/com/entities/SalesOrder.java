package com.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "sales_orders")
public class SalesOrder implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private Integer orderNumber;
  private Customer customer;
  private Double totalPrice;
  private Set<OrderLine> orderLines = new HashSet<OrderLine>(0);

  public SalesOrder() {
  }

  public SalesOrder(Customer customer) {
    this.customer = customer;
  }

  public SalesOrder(Customer customer, Set<OrderLine> orderLineses, Double totalPrice) {
    this.customer = customer;
    this.orderLines = orderLineses;
    this.totalPrice = totalPrice;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "order_number", unique = true, nullable = false)
  public Integer getOrderNumber() {
    return this.orderNumber;
  }

  public void setOrderNumber(Integer orderNumber) {
    this.orderNumber = orderNumber;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @Cascade(CascadeType.SAVE_UPDATE)
  @JoinColumn(name = "customer", nullable = false)
  public Customer getCustomer() {
    return this.customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @OneToMany(fetch = FetchType.EAGER)
  @Cascade(CascadeType.ALL)
  @JoinColumn(name = "order_number", nullable = false)
  public Set<OrderLine> getOrderLines() {
    return this.orderLines;

  }

  public void setOrderLines(Set<OrderLine> orderLineses) {
    this.orderLines = orderLineses;
  }

  @Column(name = "total_price")
  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }

}
