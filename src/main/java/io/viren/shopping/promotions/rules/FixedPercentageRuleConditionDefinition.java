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
public class FixedPercentageRuleConditionDefinition {

  @Getter(value = AccessLevel.NONE)
  private List<String> qualifiedCategories;

  @Getter(value = AccessLevel.NONE)
  private List<String> excludedSubCategories;

  @Getter(value = AccessLevel.NONE)
  private List<String> excludedProducts;

  @Getter(value = AccessLevel.NONE)
  private List<String> qualifiedProducts;

  public List<String> getExcludedSubCategories() {
    return Objects.isNull(excludedSubCategories) ? Collections.emptyList() : excludedSubCategories;
  }

  public List<String> getExcludedProducts() {
    return Objects.isNull(excludedProducts) ? Collections.emptyList() : excludedProducts;
  }

  public List<String> getQualifiedProducts() {
    return Objects.isNull(qualifiedProducts) ? Collections.emptyList() : qualifiedProducts;
  }

  public List<String> getQualifiedCategories() {
	  return Objects.isNull(qualifiedCategories) ? Collections.emptyList() : qualifiedCategories;
  }
}
