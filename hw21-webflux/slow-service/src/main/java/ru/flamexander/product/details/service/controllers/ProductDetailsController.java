package ru.flamexander.product.details.service.controllers;

import org.springframework.web.bind.annotation.*;
import ru.flamexander.product.details.service.dtos.ProductDetailsDto;
import ru.flamexander.product.details.service.exceptions.NotFoundException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/details")
public class ProductDetailsController {
    @GetMapping("/{id}")
    public ProductDetailsDto getProductDetailsById(@PathVariable Long id) throws InterruptedException {
        if (id % 2 == 0) {
            throw new NotFoundException("ProductDetails", id);
        }
        Thread.sleep(2500 + (int)(Math.random() * 2500));
        return new ProductDetailsDto(id, String.format("Product %d description", id));
    }
}
