package com.hao.demo.service;

import com.hao.demo.dto.ProductDto;
import com.hao.demo.repository.ProductRepository;
import com.hao.demo.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;


    public Flux<ProductDto> getProducts(){
        return repository.findAll().map(AppUtils::daoToDto);
    }

    public Mono<ProductDto> getProduct(String id){
        return repository.findById(id).map(AppUtils::daoToDto);
    }

    public Flux<ProductDto> getProductPriceInRange(double min, double max){
        return repository.findByPriceBetween(Range.closed(min, max)).map(AppUtils::daoToDto);
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono){
        return productDtoMono.map(AppUtils::dtoToDao)
                .flatMap(repository::insert)
                .map(AppUtils::daoToDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono,String id){
       return repository.findById(id)
                .flatMap(p->productDtoMono.map(AppUtils::dtoToDao)
                .doOnNext(e->e.setId(id)))
                .flatMap(repository::save)
                .map(AppUtils::daoToDto);
    }

    public Mono<Void> deleteProduct(String id){
        return repository.deleteById(id);
    }
}
