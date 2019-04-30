package io.viren.shopping.promotions.rules;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class BOGOFRuleConditionDefinition {

  private int qualifyingCount;

  @Getter(value = AccessLevel.NONE)
  private List<String> qualifyingCategories;

  @Getter(value = AccessLevel.NONE)
  private List<String> qualifyingProducts;

  public List<String> getQualifyingCategories() {
    return Objects.isNull(qualifyingCategories) ? Collections.emptyList() : qualifyingCategories;
  }

  public List<String> getQualifyingProducts() {
    return Objects.isNull(qualifyingProducts) ? Collections.emptyList() : qualifyingProducts;
  }
}
