package io.viren.shopping.promotions.rules.repository;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import io.viren.shopping.promotions.rules.RuleEngineType;

@Service(value = "rulesRepositoryFactory")
public class RulesRepositoryFactory {

  @Resource private RulesRepository jeasyRulesRepository;

  public RulesRepository fetchRulesRepository(final RuleEngineType engineType) {
    switch (engineType) {
      case JEASY_RULES:
        return jeasyRulesRepository;

      default:
        return jeasyRulesRepository;
    }
  }
}
