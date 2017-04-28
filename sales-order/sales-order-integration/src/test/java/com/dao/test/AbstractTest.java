package com.dao.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dao.CustomerDAO;
import com.dao.ProductDAO;
import com.dao.SalesOrderDAO;

@ContextConfiguration(locations = "classpath:META-INF/test/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public abstract class AbstractTest {
  
  @Autowired
  protected CustomerDAO customerDAO;
  @Autowired
  protected SalesOrderDAO salesOrderDAO;
  @Autowired
  protected ProductDAO productDAO;
  
}
