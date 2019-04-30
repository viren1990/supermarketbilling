package io.viren.shopping.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.viren.shopping.models.Order;

@Repository("orderRepository")
public interface OrderRepository extends CrudRepository<Order, Long>{
	
	
}
