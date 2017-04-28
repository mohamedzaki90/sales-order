package com.salesorder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProductDAO;
import com.entities.Product;
import com.salesorder.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductDAO productDAO;

  @Override
  public Product findByProductById(Integer productId) {
    return productDAO.findByPrimaryKey(productId);
  }

  @Override
  public void addProduct(Product product) {
    productDAO.persist(product);
  }

  @Override
  public void updateProduct(Product product) {
    productDAO.update(product);
  }

  @Override
  public void deleteProduct(Product product) {
    productDAO.delete(product);
  }

}
