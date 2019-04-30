package io.viren.shopping.promotions.rules;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service(value = "ruleEvaluationServiceImpl")
@Slf4j
public class RuleEvaluationServiceImpl implements RuleEvaluationService {

  @Override
  public void evaluateRules(RuleEvaluationContext context) {

    if (context.getType() == RuleEngineType.JEASY_RULES) {
      final var engine = new DefaultRulesEngine();

      final var facts = new Facts();
      facts.asMap().putAll(context.getFacts());

      engine.fire((org.jeasy.rules.api.Rules) context.getRules(), facts);
    }
    log.warn("No configured engine type available, hence skipping rules evaluation");
  }
}
