package io.viren.shopping.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import io.viren.shopping.models.Product;

@RepositoryRestResource(collectionResourceRel="products" , path="products")
@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
	
	Optional<Product> findByCode(final String code);
	
	@RestResource(exported = false)
	@Override
	default <S extends Product> S save(S entity) {
		return null;
	}

	@RestResource(exported = false)
	@Override
	default <S extends Product> Iterable<S> saveAll(Iterable<S> entities) {
		return null;
	}

	@RestResource(exported = false)
	@Override
	default void delete(Product entity) {

	}

	@RestResource(exported = false)
	@Override
	default void deleteAll() {

	}

	@RestResource(exported = false)
	@Override
	default void deleteAll(Iterable<? extends Product> entities) {

	}

	@RestResource(exported = false)
	@Override
	default void deleteById(Long id) {

	}

}
