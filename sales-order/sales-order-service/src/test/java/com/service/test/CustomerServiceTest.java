package com.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.entities.ContactInfo;
import com.entities.Customer;

public class CustomerServiceTest extends AbstractServiceTest {

  @Test
  public void testfindCustomer() {
    Customer dbCust = customerService.findCustomerById(1);
    assertNotNull("Test find customer", dbCust);
  }

  @Test
  public void testAddCustomer() {
    Customer cust = new Customer("", 0.0, 100.0, new ContactInfo());
    customerService.addCustomer(cust);
    assertNotNull("Test Add Customer", cust.getId());
  }

  @Test
  public void testUpdateCustomer() {
    Customer dbCust = customerService.findCustomerById(1);
    dbCust.setName("updatedName");
    customerService.updateCustomer(dbCust);
    Customer dbupdatedCust = customerService.findCustomerById(dbCust.getId());
    assertEquals("Test Update Customer", "updatedName", dbupdatedCust.getName());
  }

  @Test
  public void testFindAllCustomers() {
    List<Customer> customers = customerService.findAllCustomers(1, 2);
    assertEquals("Test find all", 2, customers.size());
    assertEquals(
        "Test find all -> validate first object", new Integer(2), customers.get(0).getId());
  }

}
