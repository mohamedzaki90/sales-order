package com.salesorder.service;

import java.util.List;

import com.entities.SalesOrder;
import com.salesorder.wrappers.ServiceResponse;

public interface SalesOrderService {

  /**
   * finds a sales order record by id
   * 
   * @param salesOrderId
   *          indicates order id
   * @return SalesOrder Object for the record identified by id
   */
  SalesOrder findSalesOrderById(Integer salesOrderId);

  /**
   * finds a number of sales orders - identified by maxResult - for a customer
   * 
   * @param customerId
   *          identifies a customer
   * @param start
   *          first row in the result set
   * @param maxResult
   *          maximum number of rows in the result set
   * @return a list of the specified sales orders
   */
  List<SalesOrder> findSalesOrdersForCustomer(Integer customerId, int start, int maxResult);

  /**
   * adds a sales order record to the database
   * 
   * @param order
   *          object to be added
   * @return service response wrapper with error messages if any or empty
   *         messages if success
   */
  ServiceResponse<SalesOrder> addSalesOrder(SalesOrder order);

  /**
   * updates a sales order record
   * 
   * @param order
   *          object to be updated
   * @return service response wrapper with error messages if any or empty
   *         messages if success
   */
  ServiceResponse<SalesOrder> updateSalesOrder(SalesOrder order);

  /**
   * deletes a sales order record
   * 
   * @param order
   *          object to be deleted
   * @return service response wrapper with error messages if any or empty
   *         messages if success
   */
  ServiceResponse<SalesOrder> deleteSalesOrder(SalesOrder order);

}
