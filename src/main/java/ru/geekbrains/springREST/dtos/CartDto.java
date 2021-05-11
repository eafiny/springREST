package ru.geekbrains.springREST.dtos;

import lombok.Data;
import ru.geekbrains.springREST.models.Product;
import ru.geekbrains.springREST.utils.Cart;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CartDto {
    private List<ProductDto> itemsDto;


    public CartDto(Cart cart) {
        itemsDto = cart.getItems()
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }
}
