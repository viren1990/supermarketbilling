package io.viren.shopping.promotions.rules.facts;

import java.math.BigDecimal;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderFact {
	
	private String code;
	private BigDecimal total;
	private Set<OrderEntryFact> entries;
	
	
	
}	
