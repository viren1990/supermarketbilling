package io.viren.shopping.promotions.services;

import io.viren.shopping.models.Order;

public interface PromotionEngineService {
	
	void updateOrderPromotions(final Order order);
}
