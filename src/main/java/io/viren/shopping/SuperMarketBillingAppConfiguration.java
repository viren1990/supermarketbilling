package io.viren.shopping;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.viren.shopping.promotions.rules.facts.FactProvider;
import io.viren.shopping.promotions.rules.facts.OrderFactProvider;
import io.viren.shopping.promotions.rules.facts.contexts.FactContextType;

@Configuration
@ComponentScan(basePackages = {"io.viren.shopping"})
@EnableJpaRepositories(basePackages = {"io.viren.shopping.repositories"})
public class SuperMarketBillingAppConfiguration {

  @Bean
  public Map<FactContextType, FactProvider<?>> factContextTypeAndProviders() {
    return Map.ofEntries(Map.entry(FactContextType.PROMOTION_ORDER, new OrderFactProvider()));
  }
}
