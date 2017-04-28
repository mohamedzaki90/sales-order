package com.dao.test;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import com.entities.Customer;
import com.entities.OrderLine;
import com.entities.Product;
import com.entities.SalesOrder;

public class SalesOrderDAOTest extends AbstractTest {

  @Test
  public void testFindSalesOrderForCustomer() {
    List<SalesOrder> orders = salesOrderDAO.findSalesOrdersForCustomer(1, 0, 2);
    assertThat("Sales orders for a customer", orders.size() == 2);
  }

  @Test
  public void testAddSalesOrder() {
    Product p1 = productDAO.findByPrimaryKey(1);
    Product p2 = productDAO.findByPrimaryKey(2);

    OrderLine orderLine1 = new OrderLine(p1, 1);
    OrderLine orderLine2 = new OrderLine(p2, 1);

    double total = orderLine1.getQuantity() * orderLine1.getProduct().getPrice();
    total += orderLine2.getQuantity() * orderLine2.getProduct().getPrice();

    Customer cust = customerDAO.findByPrimaryKey(1);

    SalesOrder salesOrder =
        new SalesOrder(cust, new HashSet<>(Arrays.asList(orderLine1, orderLine2)),total);
    salesOrderDAO.persist(salesOrder);

    assertThat("SalesOrder Created", salesOrder.getOrderNumber() != null);
    List<SalesOrder> customerSalesOrder =
        salesOrderDAO.findSalesOrdersForCustomer(cust.getId(), 0, 5);
    assertThat("SalesOrder Associated to the Customer", customerSalesOrder.contains(salesOrder));
  }
  
  @Test
  public void testDeleteSalesOrder(){
    salesOrderDAO.delete(null);
  }
}
