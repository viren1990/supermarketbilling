package io.viren.shopping.promotions.rules.facts;

public interface FactProvider<T> {
	
	T createFact(Object object);
	
}
