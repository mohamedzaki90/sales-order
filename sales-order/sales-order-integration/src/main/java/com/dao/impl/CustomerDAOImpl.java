package com.dao.impl;

import org.springframework.stereotype.Repository;

import com.dao.CustomerDAO;
import com.entities.Customer;

@Repository
public class CustomerDAOImpl extends AbstractDAOImpl<Customer, Integer> implements CustomerDAO{

}
