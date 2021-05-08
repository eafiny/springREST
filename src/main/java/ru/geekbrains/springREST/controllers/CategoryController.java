package ru.geekbrains.springREST.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springREST.error_handling.MarketError;
import ru.geekbrains.springREST.error_handling.ResourceNotFoundException;
import ru.geekbrains.springREST.models.Category;
import ru.geekbrains.springREST.models.Product;
import ru.geekbrains.springREST.services.CategoryService;
import ru.geekbrains.springREST.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        Optional<Category> out = categoryService.findById(id);
        return out.orElseThrow(() -> new ResourceNotFoundException("Category doesn't exist" + id));
    }
}
