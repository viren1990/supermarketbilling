package io.viren.shopping.promotions.rules.facts;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import io.viren.shopping.models.Category;
import io.viren.shopping.models.Order;
import io.viren.shopping.models.OrderEntry;
import io.viren.shopping.models.Product;

public class OrderFactProvider implements FactProvider<OrderFact> { 

  @Override
  public OrderFact createFact(Object arg) {

    final Order order = (Order) arg;
    final OrderFact fact = new OrderFact();
    fact.setCode(String.valueOf(order.getId()));
    fact.setTotal(BigDecimal.valueOf(order.getTotalPrice()));

    fact.setEntries(
        order.getEntries().stream().map(this::createEntryFact).collect(Collectors.toSet()));
    return fact;
  }

  private OrderEntryFact createEntryFact(final OrderEntry entry) {
    final OrderEntryFact entryFact = new OrderEntryFact();
    entryFact.setBasePrice(BigDecimal.valueOf(entry.getBasePrice()));
    entryFact.setQualtity(entry.getQuantity());
    entryFact.setEntryNumber(entry.getEntry());
    final ProductFact product = new ProductFact();
    product.setCode(entry.getProduct().getCode());
    product.setCategories(fetchAllSuperCategories(entry.getProduct()));

    entryFact.setProduct(product);
    return entryFact;
  }

  private void createCatFact(final Category category, final Set<CategoryFact> facts) {
    if (Objects.isNull(category.getSuperCategories()) || category.getSuperCategories().isEmpty()) {
      final CategoryFact catFact = new CategoryFact();
      catFact.setCode(category.getCode());
      facts.add(catFact);
      return;
    } else {
      final CategoryFact catFact = new CategoryFact();
      catFact.setCode(category.getCode());
      facts.add(catFact);
    }
    category.getSuperCategories().forEach(cat -> createCatFact(cat, facts));
  }

  /**
   * Fetch all categories of {@code product} upwards until root.
   *
   * @param product
   */
  private Set<CategoryFact> fetchAllSuperCategories(final Product product) {
    final Set<Category> categories = product.getSuperCategories();

    final Set<CategoryFact> facts = new HashSet<CategoryFact>();

    for (Category category : categories) {
      createCatFact(category, facts);
    }
    return facts;
  }
}
