package com.service.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.entities.Customer;
import com.entities.OrderLine;
import com.entities.Product;
import com.entities.SalesOrder;
import com.salesorder.wrappers.ServiceResponse;

public class SalesOrderServiceTest extends AbstractServiceTest {

  @Test
  public void testFindSalesOrdersForCustomer() {
    List<SalesOrder> orders = salesOrderService.findSalesOrdersForCustomer(1, 0, 2);
    assertThat("Sales orders for a customer", orders.size() == 2);
  }

  @Test
  public void testFindSalesOrderById() {
    SalesOrder order = salesOrderService.findSalesOrderById(1);
    assertNotNull("Find sales order by id", order);
  }

  @Test
  public void testAddSalesOrder() {
    Product firstProduct = productService.findByProductById(1);
    Product secondProduct = productService.findByProductById(2);

    // quantities before placing the order
    int firstProductQTY = firstProduct.getQuantity();
    int secondProductQTY = secondProduct.getQuantity();

    OrderLine firstOrderLine = new OrderLine(firstProduct, 1);
    OrderLine secondOrderLine = new OrderLine(secondProduct, 1);

    // calculate order cost
    double totalCost = firstOrderLine.getQuantity() * firstOrderLine.getProduct().getPrice();
    totalCost += secondOrderLine.getQuantity() * secondOrderLine.getProduct().getPrice();

    Customer cust = customerService.findCustomerById(1);
    // credit limit before placing the order
    double custCreditLimit = cust.getCurrentCredit();

    SalesOrder salesOrder = new SalesOrder(cust,
        new HashSet<>(Arrays.asList(firstOrderLine, secondOrderLine)), totalCost);

    ServiceResponse<SalesOrder> response = salesOrderService.addSalesOrder(salesOrder);

    assertThat("Service Response has no error messages", !response.hasMessages());
    assertThat("SalesOrder Created", salesOrder.getOrderNumber() != null);
    assertThat(
        "Products quantites updated", firstProduct.getQuantity() == firstProductQTY - 1
            && secondProduct.getQuantity() == secondProductQTY - 1);

    assertThat(
        "Customer credit updated",
        custCreditLimit + salesOrder.getTotalPrice() == cust.getCurrentCredit());
  }

  @Test @Ignore
  public void testUpdateSalesOrder() {
    Product firstProduct = productService.findByProductById(1);
    int firstProductQTY = firstProduct.getQuantity();
    OrderLine firstOrderLine = new OrderLine(firstProduct, 1);
    double totalCost = firstOrderLine.getQuantity() * firstOrderLine.getProduct().getPrice();

    Product secondProduct = productService.findByProductById(2);
    int secondProductQTY = secondProduct.getQuantity();
    OrderLine secondOrderLine = new OrderLine(secondProduct, 1);
    double updatedCost =
        totalCost + secondOrderLine.getQuantity() * secondOrderLine.getProduct().getPrice();

    Customer cust = customerService.findCustomerById(1);
    double custCreditLimit = cust.getCurrentCredit();

    SalesOrder salesOrder = new SalesOrder(cust,
        new HashSet<>(Arrays.asList(firstOrderLine, secondOrderLine)), totalCost);

    ServiceResponse<SalesOrder> response = salesOrderService.addSalesOrder(salesOrder);

    assertThat("Service Response has no error messages", !response.hasMessages());
    assertThat("SalesOrder Created", salesOrder.getOrderNumber() != null);
    assertThat(
        "Products quantites updated", firstProduct.getQuantity() == firstProductQTY - 1
            && secondProduct.getQuantity() == secondProductQTY - 1);

    assertThat(
        "Customer credit updated",
        custCreditLimit + salesOrder.getTotalPrice() == cust.getCurrentCredit());
  }

  @Test
  public void testDeleteSalesOrder() {
    SalesOrder order = salesOrderService.findSalesOrderById(1);
    Customer customer = order.getCustomer();
    double customerCurrentCredit = customer.getCurrentCredit();
    double totalPrice = order.getTotalPrice();
    salesOrderService.deleteSalesOrder(order);
    assertThat("Sales order is removed", salesOrderService.findSalesOrderById(1) == null);
    assertThat(
        "Customer credit is updated",
        customer.getCurrentCredit() == customerCurrentCredit - totalPrice);
  }

  @Test
  public void testInvalidOrderLowCustomerCredit() {
    Product product = productService.findByProductById(1);
    int productQTY = product.getQuantity();
    OrderLine orderLine = new OrderLine(product, 11);

    double totalCost = orderLine.getQuantity() * orderLine.getProduct().getPrice();

    Customer cust = customerService.findCustomerById(1);
    SalesOrder salesOrder =
        new SalesOrder(cust, new HashSet<>(Arrays.asList(orderLine)), totalCost);

    ServiceResponse<SalesOrder> response = salesOrderService.addSalesOrder(salesOrder);

    assertThat("Invalid sales order with is not created", salesOrder.getOrderNumber() == null);
    assertThat("Test sales order with low customer credit limit", response.hasMessages());
    assertThat("Product Qty is not updated", product.getQuantity() == productQTY);
  }

  @Test
  public void testInvalidOrderOutOfStock() {
    Product product = productService.findByProductById(2);
    int productQTY = product.getQuantity();

    OrderLine orderLine = new OrderLine(product, 11);
    double totalCost = orderLine.getQuantity() * orderLine.getProduct().getPrice();

    Customer cust = customerService.findCustomerById(2);
    SalesOrder salesOrder =
        new SalesOrder(cust, new HashSet<>(Arrays.asList(orderLine)), totalCost);

    ServiceResponse<SalesOrder> response = salesOrderService.addSalesOrder(salesOrder);

    assertThat("Invalid sales order is not created", salesOrder.getOrderNumber() == null);
    assertThat("Test sales order with out of stock items", response.hasMessages());
    assertThat("Product Qty is not updated", product.getQuantity() == productQTY);
  }

  @Test
  public void testInvalidOrderLowCustomerCreditAndOutOfStockItems() {
    Product product = productService.findByProductById(2);
    int productQTY = product.getQuantity();

    OrderLine orderLine = new OrderLine(product, 11);
    double totalCost = orderLine.getQuantity() * orderLine.getProduct().getPrice();

    Customer cust = customerService.findCustomerById(1);
    SalesOrder salesOrder =
        new SalesOrder(cust, new HashSet<>(Arrays.asList(orderLine)), totalCost);

    ServiceResponse<SalesOrder> response = salesOrderService.addSalesOrder(salesOrder);

    assertThat("Invalid sales order is not created", salesOrder.getOrderNumber() == null);
    assertThat(
        "Test sales order with out of stock items & low customer credit", response.hasMessages());
    assertThat("Product Qty is not updated", product.getQuantity() == productQTY);
  }

}
