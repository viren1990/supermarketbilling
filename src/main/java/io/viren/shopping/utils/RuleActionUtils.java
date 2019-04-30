package io.viren.shopping.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import io.viren.shopping.models.Order;
import io.viren.shopping.repositories.OrderRepository;

@Component
public class RuleActionUtils implements ApplicationContextAware {

  @Autowired private static ApplicationContext applicationContext;

  public static Order fetchOrderForCode(final String id) {
    return applicationContext
        .getBean(OrderRepository.class)
        .findById(Long.valueOf(id))
        .orElseThrow();
  }

  public static void saveOrder(final Order order) {
    applicationContext.getBean(OrderRepository.class).save(order);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext)
      throws BeansException { 
	  RuleActionUtils.applicationContext = applicationContext;
  }
}
