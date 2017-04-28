package com.dao.test;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.entities.ContactInfo;
import com.entities.Customer;

public class CustomerDAOTest extends AbstractTest {

  @Test
  public void testfind() {
    Customer dbCust = customerDAO.findByPrimaryKey(1);
    assertNotNull("Test find customer", dbCust);
  }

  @Test
  public void testAdd() {
    Customer cust = new Customer("", 0.0, 100.0, new ContactInfo());
    customerDAO.persist(cust);
    assertNotNull("Test Add Customer", cust.getId());
  }

  @Test
  public void testUpdate() {
    Customer dbCust = customerDAO.findByPrimaryKey(1);
    dbCust.setName("updatedName");
    customerDAO.update(dbCust);
    Customer dbupdatedCust = customerDAO.findByPrimaryKey(dbCust.getId());
    assertEquals("Test Update Customer", "updatedName", dbupdatedCust.getName());
  }

  @Test
  public void testFindAll() {
    List<Customer> customers = customerDAO.findAll(1, 2);
    assertEquals("Test find all", 2, customers.size());
    assertEquals(
        "Test find all -> validate first object", new Integer(2), customers.get(0).getId());
  }
  
  @Test
  public void testCountAll() {
    assertEquals("Test count all", Long.valueOf(3), customerDAO.getNumberOfRecords());
  }

}
