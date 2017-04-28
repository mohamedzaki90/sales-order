package com.salesorder.wrappers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Youssof
 *
 *         Wrapper class to wrap the service business validation
 * @param <T>
 *          indicates the validated Entity
 */
public class ServiceResponse<T> {
  private List<String> messages = new ArrayList<>();
  private T entity;

  public ServiceResponse(T entity) {
    this.entity = entity;
  }

  public boolean hasMessages() {
    return messages != null && !messages.isEmpty();
  }

  public List<String> addMessage(String message) {
    messages.add(message);
    return this.messages;
  }

  public List<String> getMessages() {
    return this.messages;
  }

  public T getEntity() {
    return this.entity;
  }
}
