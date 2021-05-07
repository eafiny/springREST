package ru.geekbrains.springREST.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springREST.error_handling.MarketError;
import ru.geekbrains.springREST.error_handling.ResourceNotFoundException;
import ru.geekbrains.springREST.models.Product;
import ru.geekbrains.springREST.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        Optional<Product> out = productService.findById(id);
        return out.orElseThrow(()->new ResourceNotFoundException("Product doesn't exist" + id));
    }

    @PostMapping
    public ResponseEntity<?> createNewProduct(@RequestBody Product product){
        List<String> errors = new ArrayList<>();
        if (product.getTitle().length() < 3) {
            errors.add("Too short title");
        }
        if(product.getPrice() < 0){
            errors.add("Invalid product price");
        }
        if(errors.size() > 0){
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
        }
        Product out = productService.save(product);
        return new ResponseEntity<>(out, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOneById(@PathVariable Long id){
        productService.deleteOneById(id);
    }

    @PutMapping
    public void update(@RequestBody Product product){
        productService.save(product);
    }
}
