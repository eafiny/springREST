package ru.geekbrains.springREST.utils;

import org.springframework.stereotype.Component;
import ru.geekbrains.springREST.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private List<Product> items;

    @PostConstruct
    public void init(){
        items = new ArrayList<>();
    }
}
