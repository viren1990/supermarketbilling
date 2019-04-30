package io.viren.shopping.promotions.rules.facts;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntryFact  {
	
	private int qualtity;
	private BigDecimal basePrice;
	private BigDecimal price;
	private String currencyIsoCode;	
	private int entryNumber;
	
	private ProductFact product;
	
}
