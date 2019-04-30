package io.viren.shopping.promotions.rules.facts.contexts;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import io.viren.shopping.models.Order;
import io.viren.shopping.promotions.rules.facts.FactProvider;

@Service
public class FactContextFactory {

  @Resource private Map<FactContextType, FactProvider<?>> factContextTypeAndProviders;

  public FactContext createFactContext(final FactContextType type, final Order order) {

    final FactContext context =
        new FactContext(
            type,
            Map.ofEntries(
                Map.entry(
                    type.name(),
                    factContextTypeAndProviders
                        .get(FactContextType.PROMOTION_ORDER)
                        .createFact(order))));
    return context;
  }
}
