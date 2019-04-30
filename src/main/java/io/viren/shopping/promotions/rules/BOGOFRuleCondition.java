package io.viren.shopping.promotions.rules;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import org.jeasy.rules.api.Condition;
import org.jeasy.rules.api.Facts;
import org.springframework.util.CollectionUtils;

import io.viren.shopping.promotions.rules.facts.CategoryFact;
import io.viren.shopping.promotions.rules.facts.OrderEntryFact;
import io.viren.shopping.promotions.rules.facts.OrderFact;

public class BOGOFRuleCondition implements Condition {

  private BOGOFRuleConditionDefinition conditionDefinition;

  private final Predicate<OrderEntryFact> inQualifiedCategories =
      entry -> {
        final Set<CategoryFact> categories = entry.getProduct().getCategories();
        if (Objects.nonNull(categories) && !categories.isEmpty()) {
          return categories
              .stream()
              .map(CategoryFact::getCode)
              .anyMatch(
                  category -> conditionDefinition.getQualifyingCategories().contains(category));
        }
        throw new IllegalStateException("Category can't be null for product at this point in time");
      };

  private final Predicate<OrderEntryFact> inQualifiedProducts =
      entry -> {
        return conditionDefinition.getQualifyingProducts().contains(entry.getProduct().getCode());
      };

  private final Predicate<OrderEntryFact> inQualifiedQuantities =
      entry -> entry.getQualtity() >= conditionDefinition.getQualifyingCount();

  public BOGOFRuleCondition(final BOGOFRuleConditionDefinition conditionDefinition) {
    this.conditionDefinition = conditionDefinition;
  }

  @Override
  public boolean evaluate(Facts facts) {

    final OrderFact order = facts.get("PROMOTION_ORDER");

    if (Objects.nonNull(order)) {
      final List<OrderEntryFact> qualifiedEntries =
          order.getEntries().stream().filter(this::filterEntry).collect(toList());

      // update fact
      facts.put("qualifiedEntriesFromBOGOFCondition", qualifiedEntries);
      return !CollectionUtils.isEmpty(qualifiedEntries);
    }
    throw new IllegalStateException("Order can't be null for a fact at this point in time");
  }

  private boolean filterEntry(final OrderEntryFact entry) {
    return inQualifiedCategories.and(inQualifiedQuantities).test(entry)
        || inQualifiedProducts.and(inQualifiedQuantities).test(entry);
  }
}
