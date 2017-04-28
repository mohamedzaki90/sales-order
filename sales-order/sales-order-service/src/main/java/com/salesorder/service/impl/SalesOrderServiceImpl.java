package com.salesorder.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.SalesOrderDAO;
import com.entities.OrderLine;
import com.entities.SalesOrder;
import com.salesorder.service.SalesOrderService;
import com.salesorder.wrappers.ServiceResponse;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {

  @Autowired
  private SalesOrderDAO orderDAO;

  @Override
  public SalesOrder findSalesOrderById(Integer id) {
    return orderDAO.findByPrimaryKey(id);
  }

  @Override
  public List<SalesOrder> findSalesOrdersForCustomer(Integer customerId, int start, int maxResult) {
    return orderDAO.findSalesOrdersForCustomer(customerId, start, maxResult);
  }

  @Override
  public ServiceResponse<SalesOrder> addSalesOrder(SalesOrder order) {
    ServiceResponse<SalesOrder> response = validatedSalesAddedOrder(order);
    if (response.hasMessages()) {
      return response;
    }
    
    double updatedCustomerCredit = order.getCustomer().getCurrentCredit() + order.getTotalPrice();
    order.getCustomer().setCurrentCredit(updatedCustomerCredit);

    for (OrderLine lines : order.getOrderLines()) {
      int updatedQty = lines.getProduct().getQuantity() - lines.getQuantity();
      lines.getProduct().setQuantity(updatedQty);
    }
    orderDAO.persist(order);
    return response;
  }

  @Override
  public ServiceResponse<SalesOrder> updateSalesOrder(SalesOrder order) {
    ServiceResponse<SalesOrder> response = new ServiceResponse<>(order);
    if(order == null){
      response.addMessage("Can't update sales order with null value");
      return response;
    }
    
    deleteSalesOrder(order);
    addSalesOrder(order);
    
    return response;
  }

  @Override
  public ServiceResponse<SalesOrder> deleteSalesOrder(SalesOrder order) {
    ServiceResponse<SalesOrder> response = new ServiceResponse<>(order);
    if(order == null){
      response.addMessage("Can't delete sales order with null value");
    }
    
    //return items to stock
    for(OrderLine orderLine : order.getOrderLines()){
      int updatedQty = orderLine.getQuantity() + orderLine.getProduct().getQuantity();
      orderLine.getProduct().setQuantity(updatedQty);
    }
    
    //update customer credit
    double updatedCustomerCredit = order.getCustomer().getCurrentCredit() - order.getTotalPrice();
    order.getCustomer().setCurrentCredit(updatedCustomerCredit);
    
    orderDAO.delete(order);
    return response;
  }

  private ServiceResponse<SalesOrder> validatedSalesAddedOrder(SalesOrder order) {
    ServiceResponse<SalesOrder> response = new ServiceResponse<>(order);
    if(order == null){
      response.addMessage("Can't create sales order with null value");
      return response;
    }
    Double customerCurrentCredit = order.getCustomer().getCurrentCredit();
    Double customerCreditLimit = order.getCustomer().getCreditLimit();

    if (order.getTotalPrice() + customerCurrentCredit > customerCreditLimit) {
      response.addMessage("Customer Credit Limit must be greater than the order price");
    }
    
    for (OrderLine lines : order.getOrderLines()) {
      if (lines.getQuantity() > lines.getProduct().getQuantity()) {
        response.addMessage(lines.getProduct().getDescription() + ": Quantity is Greater Than Stocked");
      }
    }
    return response;
  }

}
