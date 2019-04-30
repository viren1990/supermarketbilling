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
import io.viren.shopping.promotions.rules.facts.ProductFact;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FixedPercentageRuleCondition implements Condition {

  private FixedPercentageRuleConditionDefinition conditionDefinition;

  public FixedPercentageRuleCondition(
      final FixedPercentageRuleConditionDefinition conditionDefinition) {
    this.conditionDefinition = conditionDefinition;
  }

  private final Predicate<ProductFact> inQualifiedCategories =
      product -> {
        final Set<CategoryFact> categories = product.getCategories();
        if (Objects.nonNull(categories) && !categories.isEmpty()) {
          return categories
              .stream()
              .map(CategoryFact::getCode)
              .anyMatch(
                  category -> conditionDefinition.getQualifiedCategories().contains(category));
        }
        throw new IllegalStateException("Category can't be null for product at this point in time");
      };

  private final Predicate<ProductFact> inNonQualifiedCategories =
      product -> {
        final Set<CategoryFact> categories = product.getCategories();
        if (Objects.nonNull(categories) && !categories.isEmpty()) {
          final boolean decision =
              categories
                  .stream()
                  .map(CategoryFact::getCode)
                  .noneMatch(
                      category ->
                          conditionDefinition.getExcludedSubCategories().contains(category));
          log.info("Decision is {} ~~~~~~~~~~", decision);
          return decision;
        }
        throw new IllegalStateException("Category can't be null for product at this point in time");
      };

  private final Predicate<ProductFact> inNonQualifiedProducts =
      product -> {
        return !conditionDefinition.getExcludedProducts().contains(product.getCode());
      };

  private final Predicate<ProductFact> inQualifiedProducts =
      product -> {
        return conditionDefinition.getQualifiedProducts().contains(product.getCode());
      };

  @Override
  public boolean evaluate(Facts facts) {

    final OrderFact order = facts.get("PROMOTION_ORDER");

    if (Objects.nonNull(order)) {
      final List<OrderEntryFact> qualifiedEntries =
          order.getEntries().stream().filter(this::filterEntry).collect(toList());

      // update fact
      facts.put("qualifiedEntriesFromFPRCondition", qualifiedEntries);
      return !CollectionUtils.isEmpty(qualifiedEntries);
    }

    final ProductFact product = facts.get("PROMOTION_PRODUCT");
    if (Objects.nonNull(product)) {
      return inNonQualifiedProducts.negate().test(product);
    }

    throw new IllegalStateException("Order/Product can't be null for a fact at this point in time");
  }

  private boolean filterEntry(final OrderEntryFact entry) {
    return inQualifiedCategories
        .and(inNonQualifiedCategories)
        .and(inNonQualifiedProducts)
        .or(inQualifiedProducts)
        .test(entry.getProduct());
  }
}
