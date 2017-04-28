package com.rest.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.entities.Customer;
import com.salesorder.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerRestAPI {
  
  @Autowired
  private CustomerService customerService;
  
  /**
   * @param id
   * @return
   */
  @RequestMapping(value = "/findCustomer", method = RequestMethod.GET)
  public ResponseEntity<Customer> findCustomerById(Integer id){
    Customer customer = customerService.findCustomerById(id);
    if(customer != null){
      return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  /**
   * @param startIndex
   * @param maxResults
   * @return
   */
  @RequestMapping(value = "/listCustomers", method = RequestMethod.GET)
  public ResponseEntity<List<Customer>> listAllCustomers(Integer startIndex, Integer maxResults) {
    List<Customer> customers = customerService.findAllCustomers(startIndex, maxResults);
    return new ResponseEntity<>(customers,HttpStatus.OK);
  }
  
  /**
   * @param customer
   * @param result
   * @return
   */
  @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
  public ResponseEntity<?> addCustomer(@Valid Customer customer, BindingResult result){
    if(result.hasErrors()){
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    customerService.addCustomer(customer);
    return new ResponseEntity<>(result.getAllErrors(), HttpStatus.OK);
  }
  
  /**
   * @param customer
   * @return
   */
  @RequestMapping(value = "/updateCustomer", method = RequestMethod.PUT)
  public ResponseEntity<?> updateCustomer(@Valid Customer customer, BindingResult result){
    if(result.hasErrors()){
      return new ResponseEntity<>(result.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
    }
    customerService.updateCustomer(customer);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
