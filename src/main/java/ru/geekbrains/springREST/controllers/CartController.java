package ru.geekbrains.springREST.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springREST.models.Product;
import ru.geekbrains.springREST.services.ProductService;
import ru.geekbrains.springREST.utils.Cart;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final Cart cart;
    private final ProductService productService;

    @GetMapping("/add")
    public List<Product> addProductToCart(@RequestParam Long id){
        cart.add(productService.findById(id).get());
        log.info("log: В корзину добавлен продукт: " + cart.getItems());
        return cart.getItems();
    }

    @GetMapping("/clean")
    public ResponseEntity cleanTheCart(@RequestParam int cartCleanStatus){
        if(cartCleanStatus == 1){
            cart.clean();
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
