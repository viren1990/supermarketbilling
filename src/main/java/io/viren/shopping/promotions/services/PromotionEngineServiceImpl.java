package io.viren.shopping.promotions.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import io.viren.shopping.models.Order;
import io.viren.shopping.promotions.rules.RuleEngineType;
import io.viren.shopping.promotions.rules.RuleEvaluationContext;
import io.viren.shopping.promotions.rules.RuleEvaluationService;
import io.viren.shopping.promotions.rules.facts.contexts.FactContext;
import io.viren.shopping.promotions.rules.facts.contexts.FactContextFactory;
import io.viren.shopping.promotions.rules.facts.contexts.FactContextType;
import io.viren.shopping.promotions.rules.repository.RulesRepositoryFactory;

@Service(value = "promotionEngineService")
public class PromotionEngineServiceImpl implements PromotionEngineService {

  @Resource private FactContextFactory factContextFactory;

  @Resource private RuleEvaluationService ruleEvaluationService;

  @Resource(name = "rulesRepositoryFactory")
  private RulesRepositoryFactory rulesRepositoryFactory;

  @Override
  public void updateOrderPromotions(Order order) {

    final FactContext factContext =
        factContextFactory.createFactContext(FactContextType.PROMOTION_ORDER, order);

    RuleEvaluationContext evaluationContext =
        new RuleEvaluationContext(
            factContext.getFacts(),
            RuleEngineType.JEASY_RULES,
            rulesRepositoryFactory.fetchRulesRepository(RuleEngineType.JEASY_RULES).fetchRules());

    ruleEvaluationService.evaluateRules(evaluationContext);
  }
}
