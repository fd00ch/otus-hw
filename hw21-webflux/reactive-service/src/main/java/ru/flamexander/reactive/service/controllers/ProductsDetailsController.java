package ru.flamexander.reactive.service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.flamexander.reactive.service.dtos.DetailedProductDto;
import ru.flamexander.reactive.service.dtos.ProductDetailsDto;
import ru.flamexander.reactive.service.services.DetailedProductsService;
import ru.flamexander.reactive.service.services.ProductDetailsService;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/detailed")
@RequiredArgsConstructor
public class ProductsDetailsController {
    private final ProductDetailsService productDetailsService;
    private final DetailedProductsService detailedProductsService;

    @GetMapping("/demo")
    public Flux<ProductDetailsDto> getManySlowProducts() {
        Mono<ProductDetailsDto> p1 = productDetailsService.getProductDetailsById(1L);
        Mono<ProductDetailsDto> p2 = productDetailsService.getProductDetailsById(2L);
        Mono<ProductDetailsDto> p3 = productDetailsService.getProductDetailsById(3L);
        return p1.mergeWith(p2).mergeWith(p3);
    }
    @GetMapping("/{id}")
    public Mono<DetailedProductDto> getDetailedProductById(@PathVariable Long id) {
        return detailedProductsService.findById(id);
    }

    @GetMapping
    public Flux<DetailedProductDto> getAllDetailedProducts(@RequestParam(required = false) Set<Long> ids) {
        return detailedProductsService.findAll(ids);
    }
}
