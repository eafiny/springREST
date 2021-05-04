package ru.geekbrains.springREST.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springREST.models.Product;
import ru.geekbrains.springREST.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/api/v1/products")
    @ResponseBody
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/api/v1/products/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.findById(id).get();
    }

    @PostMapping
    public Product createNewProduct(@RequestBody Product product){
        return productService.createNewProduct(product);
    }

    @DeleteMapping("/api/v1/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOneById(@PathVariable Long id){
        productService.deleteOneById(id);
    }
}
