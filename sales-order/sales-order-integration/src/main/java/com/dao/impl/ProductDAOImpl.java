package com.dao.impl;

import org.springframework.stereotype.Repository;

import com.dao.ProductDAO;
import com.entities.Product;

@Repository
public class ProductDAOImpl extends AbstractDAOImpl<Product, Integer> implements ProductDAO {

}
