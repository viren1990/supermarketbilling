package io.viren.shopping.promotions.rules.facts.contexts;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FactContext {
	
	private FactContextType type;
	
	private Map<String ,Object> facts;
}
