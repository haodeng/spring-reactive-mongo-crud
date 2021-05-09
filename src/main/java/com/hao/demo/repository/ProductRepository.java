package com.hao.demo.repository;

import com.hao.demo.dao.ProductDao;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<ProductDao, String> {
    Flux<ProductDao> findByPriceBetween(Range<Double> priceRange);
}
