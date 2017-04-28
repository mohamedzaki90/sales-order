package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.dao.SalesOrderDAO;
import com.entities.SalesOrder;

@SuppressWarnings("unchecked")
@Repository
public class SalesOrderDAOImpl extends AbstractDAOImpl<SalesOrder, Integer>
    implements SalesOrderDAO {

  @Override
  public List<SalesOrder> findSalesOrdersForCustomer(Integer customerId, int start, int maxResults) {
    String queryString = "select s from SalesOrder s where s.customer.id= :customerId";
    Query query = getSession().createQuery(queryString);
    query.setParameter("customerId", customerId);
    query.setFirstResult(start);
    query.setMaxResults(maxResults);
    return query.list();
  }

}
