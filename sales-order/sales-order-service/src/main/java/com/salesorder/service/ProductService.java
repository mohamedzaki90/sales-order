package com.salesorder.service;

import com.entities.Product;

public interface ProductService {

  /**
   * finds a product record by id
   * 
   * @param productId
   *          identifies product id
   * @return a product object for the specified record or null if no such a
   *         record
   */
  Product findByProductById(Integer productId);

  /**
   * adds a product record to the database
   * 
   * @param product
   *          object to be added
   */
  void addProduct(Product product);

  /**
   * updates product record
   * 
   * @param product
   *          object to be updated
   */
  void updateProduct(Product product);

  /**
   * deletes a product record from the database
   * 
   * @param product
   *          object to be removed
   */
  void deleteProduct(Product product);
}
