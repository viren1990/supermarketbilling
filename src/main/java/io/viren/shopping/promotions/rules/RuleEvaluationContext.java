package io.viren.shopping.promotions.rules;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RuleEvaluationContext {
	
	private Map<String, Object> facts;
	
	private RuleEngineType type;
	
	private PromotionRules rules;
	
	
}

