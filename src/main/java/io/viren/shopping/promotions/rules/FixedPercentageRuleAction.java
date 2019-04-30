package io.viren.shopping.promotions.rules;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jeasy.rules.api.Action;
import org.jeasy.rules.api.Facts;

import io.viren.shopping.models.OrderEntry;
import io.viren.shopping.promotions.rules.facts.OrderEntryFact;
import io.viren.shopping.promotions.rules.facts.OrderFact;
import io.viren.shopping.promotions.rules.facts.ProductFact;
import io.viren.shopping.utils.RuleActionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FixedPercentageRuleAction implements Action {

  private FixedPercentageRuleActionDefinition actionDefinition;


  public FixedPercentageRuleAction(final FixedPercentageRuleActionDefinition actionDefinition) {
    this.actionDefinition = actionDefinition;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute(Facts facts) throws Exception {

    final var qualifiedEntries =
        (List<OrderEntryFact>) facts.get("qualifiedEntriesFromFPRCondition");

    final var orderFact = (OrderFact) facts.get("PROMOTION_ORDER");

    log.info(
        "Applied \"Fixed Percentage discounts of {} \"on all qualified order entries , e.g.[{}]",
        actionDefinition.getPercentageDiscount(),
        qualifiedEntries
            .stream()
            .map(OrderEntryFact::getProduct)
            .map(ProductFact::getCode)
            .collect(Collectors.joining(",")));

    log.info("~~~~~~~~~~ Before action execution, order total is {}",orderFact.getTotal());
    
    final var order = RuleActionUtils.fetchOrderForCode(orderFact.getCode());
    final var entries =
        qualifiedEntries
            .stream()
            .map(
                entryFact ->
                    order
                        .getEntries()
                        .stream()
                        .filter(entry -> entry.getEntry() == entryFact.getEntryNumber())
                        .findAny()
                        .orElseThrow())
            .collect(Collectors.toList());
    entries.forEach(
        entry -> {
          Double totalPrice = entry.getTotalPrice();

          final Double discountedPrice =
              totalPrice * actionDefinition.getPercentageDiscount() / 100;
          entry.setDiscountedPrice(discountedPrice);
          final Double updatedTotalPrice = totalPrice = totalPrice - discountedPrice;
          entry.setTotalPrice(updatedTotalPrice);
        });

    final Double updatedTotalPrice = 
            order.getEntries().stream().mapToDouble(OrderEntry::getTotalPrice).reduce(0, Double::sum);
    orderFact.setTotal(BigDecimal.valueOf(updatedTotalPrice));
    log.info("~~~~~~~~~~ After action execution, order total is {}",orderFact.getTotal()); 
   
    order.setSubTotal(updatedTotalPrice);
    //subtotal is total price as of now.
    order.setTotalPrice(updatedTotalPrice);

    final Double updatedTotalDiscount =
        Double.sum(
            Optional.ofNullable(order.getTotalDiscount()).orElse(0.0),
            entries.stream().mapToDouble(OrderEntry::getDiscountedPrice).reduce(0, Double::sum));

    order.setTotalDiscount(updatedTotalDiscount);
    RuleActionUtils.saveOrder(order);
  }
}
