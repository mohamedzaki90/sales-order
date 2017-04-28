package com.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.AbstractDAO;

@SuppressWarnings("unchecked")
public abstract class AbstractDAOImpl<T, PK extends Serializable> implements AbstractDAO<T, PK> {

  @Autowired
  private SessionFactory sessionFactory;

  private final Class<T> persistentEntity;

  public AbstractDAOImpl() {
    this.persistentEntity =
        (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  protected Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  protected Criteria createEntityCriteria() {
    return getSession().createCriteria(persistentEntity);
  }

  @Override
  public T findByPrimaryKey(PK key) {
    return (T) getSession().get(persistentEntity, key);
  }

  @Override
  public List<T> findAll(int startIndex, int maxResults) {
    Criteria criteria = createEntityCriteria();
    criteria.setFirstResult(startIndex);
    criteria.setMaxResults(maxResults);
    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    return (List<T>) criteria.list();
  }

  @Override
  public void persist(T entity) {
    getSession().persist(entity);
  }

  @Override
  public void merge(T entity) {
    getSession().merge(entity);
  }

  @Override
  public void update(T entity) {
    getSession().update(entity);
  }

  @Override
  public void delete(T entity) {
    getSession().delete(entity);
  }

  @Override
  public Long getNumberOfRecords() {
    Long result =
        (Long) createEntityCriteria().setProjection(Projections.rowCount()).uniqueResult();
    return result != null ? result : 0;
  }

}
