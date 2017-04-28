package com.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customers")
// @JsonIgnoreProperties({ "salesOrderses" })
public class Customer implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private Integer id;
  private String name;
  private Double creditLimit;
  private Double currentCredit;
  private ContactInfo contactInfo;
  private Set<SalesOrder> salesOrders = new HashSet<SalesOrder>(0);

  public Customer() {
  }

  public Customer(String name, Double creditLimit, Double currentCredit, ContactInfo contactInfo) {
    this.name = name;
    this.creditLimit = creditLimit;
    this.currentCredit = currentCredit;
    this.contactInfo = contactInfo;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "code", unique = true, nullable = false)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "name", length = 45)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "credit_limit", precision = 22, scale = 0)
  public Double getCreditLimit() {
    return creditLimit;
  }

  public void setCreditLimit(Double creditLimit) {
    this.creditLimit = creditLimit;
  }

  @Column(name = "current_credit", precision = 22, scale = 0)
  public Double getCurrentCredit() {
    return currentCredit;
  }

  public void setCurrentCredit(Double currentCredit) {
    this.currentCredit = currentCredit;
  }

  @Embedded
  public ContactInfo getContactInfo() {
    return this.contactInfo;
  }

  public void setContactInfo(ContactInfo contactInfo) {
    this.contactInfo = contactInfo;
  }

  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
  @Cascade(CascadeType.DELETE)
  public Set<SalesOrder> getSalesOrders() {
    return this.salesOrders;
  }

  public void setSalesOrders(Set<SalesOrder> salesOrders) {
    this.salesOrders = salesOrders;
  }

}
