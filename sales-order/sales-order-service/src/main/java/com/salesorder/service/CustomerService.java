package com.salesorder.service;

import java.util.List;

import com.entities.Customer;

/**
 * @author Youssof
 *
 */
public interface CustomerService {

  /**
   * finds a customer record by id
   * 
   * @param customerId
   *          identifies customer id
   * @return a customer object for the specified record or null if no such
   *         record
   */
  public Customer findCustomerById(Integer customerId);

  /**
   * Lists a number of customer records (identified by maxResults) starting from
   * startIndex
   * 
   * @param startIndex
   *          indicates the starting row number
   * @param maxResults
   *          indicates the maximum number of records
   * @return List of Customer entities
   */
  List<Customer> listAll(int startIndex, int maxResults);

  /**
   * adds a customer record to the database
   * 
   * @param customer
   *          object to be saved
   */
  public void addCustomer(Customer customer);

  /**
   * updates a customer record
   * 
   * @param customer
   *          object to be updated
   */
  public void updateCustomer(Customer customer);

  /**
   * deletes a customer record from the database
   * 
   * @param customer
   *          object to be deleted
   */
  public void deleteCustomer(Customer customer);

  /**
   * find a number of customer records starting from startIndex
   * 
   * @param startIndex
   *          indicates the first row in the result
   * @param maxResults
   *          indicates the number of records in the result set
   * @return list of customer entities
   */
  public List<Customer> findAllCustomers(int startIndex, int maxResults);
}
