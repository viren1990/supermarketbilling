package io.viren.shopping.promotions.rules.repository;

import java.util.List;

import org.jeasy.rules.core.RuleBuilder;
import org.jeasy.rules.support.ActivationRuleGroup;
import org.springframework.stereotype.Service;

import io.viren.shopping.promotions.rules.BOGOFRuleAction;
import io.viren.shopping.promotions.rules.BOGOFRuleActionDefinition;
import io.viren.shopping.promotions.rules.BOGOFRuleCondition;
import io.viren.shopping.promotions.rules.BOGOFRuleConditionDefinition;
import io.viren.shopping.promotions.rules.FixedPercentageRuleAction;
import io.viren.shopping.promotions.rules.FixedPercentageRuleActionDefinition;
import io.viren.shopping.promotions.rules.FixedPercentageRuleCondition;
import io.viren.shopping.promotions.rules.FixedPercentageRuleConditionDefinition;
import io.viren.shopping.promotions.rules.PromotionRules;

@Service(value = "jeasyRulesRepository")
public class JeasyRulesRepository implements RulesRepository {

  @Override
  public PromotionRules fetchRules() {
    final PromotionRules rules = new PromotionRules();

    final ActivationRuleGroup group = new ActivationRuleGroup();
    group.setPriority(200);
    // group.addRule(new CategoryRule1());
    group.addRule(
        new RuleBuilder()
            .name("promoRule2")
            .description("10% Fixed Percentage Discount Offer On All Produce Products")
            .priority(3)
            .when(
                new FixedPercentageRuleCondition(
                    new FixedPercentageRuleConditionDefinition(
                        List.of("Produce"), null, null, null)))
            .then(new FixedPercentageRuleAction(new FixedPercentageRuleActionDefinition(10.0)))
            .build());
    group.addRule(
        new RuleBuilder()
            .name("promoRule1")
            .description("18% Fixed Percentage Discount Offer On All Fruits")
            .priority(2)
            .when(
                new FixedPercentageRuleCondition(
                    new FixedPercentageRuleConditionDefinition(
                        List.of("Fruits"), null, null, null)))
            .then(new FixedPercentageRuleAction(new FixedPercentageRuleActionDefinition(18.0)))
            .build());

    final BOGOFRuleConditionDefinition conditionDefinition =
        new BOGOFRuleConditionDefinition(3, null, List.of("Apple"));

    group.addRule(
        new RuleBuilder()
            .name("promoRule3")
            .description("Buy 3 Get 1 free")
            .priority(1)
            .when(new BOGOFRuleCondition(conditionDefinition))
            .then(new BOGOFRuleAction(new BOGOFRuleActionDefinition(1), conditionDefinition))
            .build());

    final ActivationRuleGroup group2 = new ActivationRuleGroup();
    group2.setPriority(400);
    // group.addRule(new CategoryRule1());
    group2.addRule(
        new RuleBuilder()
            .name("promoRule2")
            .description("10% Fixed Percentage Discount Offer On All Produce Products")
            .priority(6)
            .when(
                new FixedPercentageRuleCondition(
                    new FixedPercentageRuleConditionDefinition(
                        List.of("Produce"), null, null, null)))
            .then(new FixedPercentageRuleAction(new FixedPercentageRuleActionDefinition(10.0)))
            .build());
    group2.addRule(
        new RuleBuilder()
            .name("promoRule1")
            .description("18% Fixed Percentage Discount Offer On All Fruits")
            .priority(5)
            .when(
                new FixedPercentageRuleCondition(
                    new FixedPercentageRuleConditionDefinition(
                        List.of("Fruits"), null, null, null)))
            .then(new FixedPercentageRuleAction(new FixedPercentageRuleActionDefinition(18.0)))
            .build());
    group2.addRule(
        new RuleBuilder()
            .name("promoRule3")
            .description("20% Fixed Percentage Discount Offer On All Oranges")
            .priority(4)
            .when(
                new FixedPercentageRuleCondition(
                    new FixedPercentageRuleConditionDefinition(
                        null, null, null, List.of("Orange"))))
            .then(new FixedPercentageRuleAction(new FixedPercentageRuleActionDefinition(20.0)))
            .build());

    final ActivationRuleGroup group3 = new ActivationRuleGroup();
    group3.setPriority(500);
    // group.addRule(new CategoryRule1());
    group3.addRule(
        new RuleBuilder()
            .name("promoRule2")
            .description("10% Fixed Percentage Discount Offer On All Produce Products")
            .priority(6)
            .when(
                new FixedPercentageRuleCondition(
                    new FixedPercentageRuleConditionDefinition(
                        List.of("Produce"), null, null, null)))
            .then(new FixedPercentageRuleAction(new FixedPercentageRuleActionDefinition(10.0)))
            .build());
    group3.addRule(
        new RuleBuilder()
            .name("promoRule1")
            .description("5% Fixed Percentage Discount Offer On All Veg")
            .priority(5)
            .when(
                new FixedPercentageRuleCondition(
                    new FixedPercentageRuleConditionDefinition(List.of("Veg"), null, null, null)))
            .then(new FixedPercentageRuleAction(new FixedPercentageRuleActionDefinition(5.0)))
            .build());
    
    final BOGOFRuleConditionDefinition definition = 
            new BOGOFRuleConditionDefinition(5, null, List.of("Potato"));
    group3.addRule(
        new RuleBuilder()
            .name("promoRule3")
            .description("Buy 5 Get 2 free")
            .priority(1)
            .when(
                new BOGOFRuleCondition(definition))
            .then(new BOGOFRuleAction(new BOGOFRuleActionDefinition(2),definition))
            .build());

    final ActivationRuleGroup group4 = new ActivationRuleGroup();
    group4.setPriority(520);
    // group.addRule(new CategoryRule1());
    group4.addRule(
        new RuleBuilder()
            .name("promoRule2")
            .description("10% Fixed Percentage Discount Offer On All Produce Products")
            .priority(6)
            .when(
                new FixedPercentageRuleCondition(
                    new FixedPercentageRuleConditionDefinition(
                        List.of("Produce"), null, null, null)))
            .then(new FixedPercentageRuleAction(new FixedPercentageRuleActionDefinition(10.0)))
            .build());
    group4.addRule(
        new RuleBuilder()
            .name("promoRule1")
            .description("5% Fixed Percentage Discount Offer On All Veg")
            .priority(5)
            .when(
                new FixedPercentageRuleCondition(
                    new FixedPercentageRuleConditionDefinition(List.of("Veg"), null, null, null)))
            .then(new FixedPercentageRuleAction(new FixedPercentageRuleActionDefinition(5.0)))
            .build());
    group4.addRule(
        new RuleBuilder()
            .name("promoRule3")
            .description("10% Fixed Percentage Discount Offer On All Tomatoes")
            .priority(4)
            .when(
                new FixedPercentageRuleCondition(
                    new FixedPercentageRuleConditionDefinition(
                        null, null, null, List.of("Tomato"))))
            .then(new FixedPercentageRuleAction(new FixedPercentageRuleActionDefinition(10.0)))
            .build());

    final ActivationRuleGroup group5 = new ActivationRuleGroup();
    group5.setPriority(550);
    // group.addRule(new CategoryRule1());
    group5.addRule(
        new RuleBuilder()
            .name("promoRule2")
            .description("15% Fixed Percentage Discount Offer On All Dairy Products")
            .priority(6)
            .when(
                new FixedPercentageRuleCondition(
                    new FixedPercentageRuleConditionDefinition(List.of("Dairy"), null, null, null)))
            .then(new FixedPercentageRuleAction(new FixedPercentageRuleActionDefinition(15.0)))
            .build());
    group5.addRule(
        new RuleBuilder()
            .name("promoRule1")
            .description("20% Fixed Percentage Discount Offer On All Milk products")
            .priority(5)
            .when(
                new FixedPercentageRuleCondition(
                    new FixedPercentageRuleConditionDefinition(List.of("Milk"), null, null, null)))
            .then(new FixedPercentageRuleAction(new FixedPercentageRuleActionDefinition(20.0)))
            .build());
   
    final BOGOFRuleConditionDefinition conDefinition = 
            new BOGOFRuleConditionDefinition(3, null, List.of("Cow Milk"));
    group5.addRule(
        new RuleBuilder()
            .name("promoRule3")
            .description("Buy 3 Get 1 free")
            .priority(1)
            .when(
                new BOGOFRuleCondition(conDefinition))
            .then(new BOGOFRuleAction(new BOGOFRuleActionDefinition(1),conDefinition))
            .build());

    final ActivationRuleGroup group6 = new ActivationRuleGroup();
    group6.setPriority(570);
    // group.addRule(new CategoryRule1());
    group6.addRule(
        new RuleBuilder()
            .name("promoRule2")
            .description("15% Fixed Percentage Discount Offer On All Dairy Products")
            .priority(6)
            .when(
                new FixedPercentageRuleCondition(
                    new FixedPercentageRuleConditionDefinition(List.of("Dairy"), null, null, null)))
            .then(new FixedPercentageRuleAction(new FixedPercentageRuleActionDefinition(15.0)))
            .build());
    group6.addRule(
        new RuleBuilder()
            .name("promoRule1")
            .description("20% Fixed Percentage Discount Offer On All Cheese products")
            .priority(2)
            .when(
                new FixedPercentageRuleCondition(
                    new FixedPercentageRuleConditionDefinition(
                        List.of("Cheese"), null, null, null)))
            .then(new FixedPercentageRuleAction(new FixedPercentageRuleActionDefinition(20.0)))
            .build());
    group6.addRule(
        new RuleBuilder()
            .name("promoRule3")
            .description("10% Fixed Percentage Discount Offer On All Gouda Cheese")
            .priority(3)
            .when(
                new FixedPercentageRuleCondition(
                    new FixedPercentageRuleConditionDefinition(null, null, null, List.of("Gouda"))))
            .then(new FixedPercentageRuleAction(new FixedPercentageRuleActionDefinition(10.0)))
            .build());

    rules.register(group);
    rules.register(group2);
    rules.register(group3);
    rules.register(group4);
    rules.register(group5);
    rules.register(group6);
    return rules;
  }
}
