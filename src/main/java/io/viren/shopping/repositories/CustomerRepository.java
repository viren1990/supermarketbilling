package io.viren.shopping.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import io.viren.shopping.models.Customer;

@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

	Optional<Customer> findByUid(@Param(value = "customer") final String customerId);

	@RestResource(exported = false)
	@Override
	default <S extends Customer> S save(S entity) {
		return null;
	}

	@RestResource(exported = false)
	@Override
	default <S extends Customer> Iterable<S> saveAll(Iterable<S> entities) {
		return null;
	}

	@RestResource(exported = false)
	@Override
	default void delete(Customer entity) {

	}

	@RestResource(exported = false)
	@Override
	default void deleteAll() {

	}

	@RestResource(exported = false)
	@Override
	default void deleteAll(Iterable<? extends Customer> entities) {

	}

	@RestResource(exported = false)
	@Override
	default void deleteById(Long id) {

	}
}
