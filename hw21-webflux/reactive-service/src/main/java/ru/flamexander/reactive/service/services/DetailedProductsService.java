package ru.flamexander.reactive.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.flamexander.reactive.service.dtos.DetailedProductDto;
import ru.flamexander.reactive.service.dtos.ProductDetailsDto;
import ru.flamexander.reactive.service.entities.Product;
import ru.flamexander.reactive.service.exceptions.NotFoundException;

import java.util.Set;
import java.util.function.BiFunction;

@Service
@RequiredArgsConstructor
public class DetailedProductsService {
    private final ProductsService productsService;
    private final ProductDetailsService productDetailsService;

    public Mono<DetailedProductDto> findById(Long id) {
        var product = productsService.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new NotFoundException("Product", id))));
        var productDetailed = productDetailsService.getProductDetailsById(id);
        return Mono.zip(product, productDetailed, productToDetailedProductDto);
    }

    public Flux<DetailedProductDto> findAll(Set<Long> ids) {
        var products = ids == null || ids.isEmpty()
                ? productsService.findAll()
                : productsService.findAllByIds(ids);
        var result = products
                .flatMap(product -> {
                    var productDetailedDTO = productDetailsService.getProductDetailsById(product.getId());
                    return productDetailedDTO
                            .zipWith(Mono.just(product))
                            .map(tuple -> productToDetailedProductDto.apply(tuple.getT2(), tuple.getT1()));
                })
                .sort();
        return result;
    }

    private final BiFunction<Product, ProductDetailsDto, DetailedProductDto> productToDetailedProductDto =
            (product, details) -> {
        var dto = DetailedProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(details.getDescription())
                .build();
        return dto;
    };
}
