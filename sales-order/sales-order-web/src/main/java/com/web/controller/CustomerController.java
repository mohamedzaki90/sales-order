package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entities.Customer;
import com.salesorder.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	/**
	 * This method is to expose find all customers in the customers table
	 * @return response entity with list of customers
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String listAllCustomers() {
		return "/pages/test";
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<Customer> get(@RequestParam Integer id) {
		Customer dbcust = customerService.findCustomerById(id);
		return new ResponseEntity<Customer>(dbcust,HttpStatus.OK);
	}

}
