package com.dao;

import java.io.Serializable;
import java.util.List;

public interface AbstractDAO<T, PK extends Serializable> {

  List<T> findAll(int startIndex, int maxResults);

  T findByPrimaryKey(PK pk);

  void persist(T entity);

  void merge(T entity);
  
  void update(T entity);

  void delete(T entity);

  Long getNumberOfRecords();

}
