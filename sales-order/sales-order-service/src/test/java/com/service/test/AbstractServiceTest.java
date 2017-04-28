package com.service.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.salesorder.service.CustomerService;
import com.salesorder.service.ProductService;
import com.salesorder.service.SalesOrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/test/test-context.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public abstract class AbstractServiceTest {

  @Autowired
  protected CustomerService customerService;
  @Autowired
  protected SalesOrderService salesOrderService;
  @Autowired
  protected ProductService productService;

}
