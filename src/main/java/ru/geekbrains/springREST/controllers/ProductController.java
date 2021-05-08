package ru.geekbrains.springREST.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springREST.dtos.ProductDto;
import ru.geekbrains.springREST.error_handling.InvalidDataException;
import ru.geekbrains.springREST.error_handling.MarketError;
import ru.geekbrains.springREST.error_handling.ResourceNotFoundException;
import ru.geekbrains.springREST.models.Category;
import ru.geekbrains.springREST.models.Product;
import ru.geekbrains.springREST.services.CategoryService;
import ru.geekbrains.springREST.services.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam(name = "p", defaultValue = "1") int page){
        Page<Product> productPage = productService.findPage(page - 1, 10);
        Page<ProductDto> dtoPage = new PageImpl<>(productPage.getContent()
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList()),
                productPage.getPageable(), productPage.getTotalElements());
        return dtoPage;
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        Product out = productService.findById(id).orElseThrow(()->new ResourceNotFoundException("Product doesn't exist" + id));
        return new ProductDto(out);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<String> messages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getObjectName)
                    .collect(Collectors.toList());
            throw new InvalidDataException(messages);
        }
        return productService.createNewProduct(productDto);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        return productService.updateProduct(productDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteOneById(@RequestParam Long id){
        productService.deleteOneById(id);
    }
}
