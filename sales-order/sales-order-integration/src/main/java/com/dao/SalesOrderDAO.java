package com.dao;

import java.util.List;

import com.entities.SalesOrder;

public interface SalesOrderDAO extends AbstractDAO<SalesOrder, Integer> {

  /**
   * finds a number of sales orders - identified by maxResults - for a customer
   * 
   * @param customerId
   *          identifies a customer
   * @param start
   *          first row in the result set
   * @param maxResults
   *          maximum number of rows in the result set
   * @return a list of the specified sales orders
   */
  List<SalesOrder> findSalesOrdersForCustomer(Integer customerId, int start, int maxResults);

}
