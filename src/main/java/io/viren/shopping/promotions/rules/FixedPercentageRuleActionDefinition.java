package io.viren.shopping.promotions.rules;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FixedPercentageRuleActionDefinition {
	
	private double percentageDiscount;
	
}
