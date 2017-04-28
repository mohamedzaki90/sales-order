package com.salesorder.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.CustomerDAO;
import com.entities.Customer;
import com.salesorder.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerDAO customerDAO;

  @Override
  public Customer findCustomerById(Integer customerId) {
    return customerDAO.findByPrimaryKey(customerId);
  }

  @Override
  public List<Customer> listAll(int startIndex, int maxResults) {
    return customerDAO.findAll(startIndex, maxResults);
  }

  @Override
  public void addCustomer(Customer customer) {
    customerDAO.persist(customer);
  }

  @Override
  public void updateCustomer(Customer customer) {
    customerDAO.update(customer);
  }

  @Override
  public void deleteCustomer(Customer customer) {
    customerDAO.delete(customer);    
  }

  @Override
  public List<Customer> findAllCustomers(int startIndex, int maxResults) {
    return customerDAO.findAll(startIndex, maxResults);
  }

}
