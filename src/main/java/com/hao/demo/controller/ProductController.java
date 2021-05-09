package com.hao.demo.controller;

import com.hao.demo.dto.ProductDto;
import com.hao.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public Flux<ProductDto> getProducts(){
        return service.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> getProduct(@PathVariable String id){
        return service.getProduct(id);
    }

    @GetMapping("/price-range")
    public Flux<ProductDto> getProductPriceBetweenRange(@RequestParam("min") double min,
                                                        @RequestParam("max")double max){
        return service.getProductPriceInRange(min, max);
    }

    @PostMapping
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDtoMono){
        return service.saveProduct(productDtoMono);
    }

    @PutMapping("/{id}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono,
                                          @PathVariable String id){
        return service.updateProduct(productDtoMono,id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return service.deleteProduct(id);
    }
}
