package io.viren.shopping.promotions.rules.facts;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductFact {
	
	private String code;
	
	private Set<CategoryFact> categories;

}
