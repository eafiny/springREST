package ru.geekbrains.springREST.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.springREST.dtos.ProductDto;
import ru.geekbrains.springREST.error_handling.ResourceNotFoundException;
import ru.geekbrains.springREST.models.Category;
import ru.geekbrains.springREST.models.Product;
import ru.geekbrains.springREST.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Page<Product> findPage(int page, int pageSize){
        return productRepository.findAllBy(PageRequest.of(page, pageSize));
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    @Transactional
    public ProductDto  createNewProduct(ProductDto productDto){
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(()->new ResourceNotFoundException("Category doesn't exist"));
        product.setCategory(category);
        productRepository.save(product);
        return  new ProductDto(product);
    }

    @Transactional
    public ProductDto  updateProduct(ProductDto productDto){
        Product product = findById(productDto.getId()).orElseThrow(()->new ResourceNotFoundException("Product doesn't exist" + productDto.getId() + "(for update"));
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(()->new ResourceNotFoundException("Category doesn't exist"));
        product.setCategory(category);
        return  new ProductDto(product);
    }

    public void deleteOneById(Long id) {
        productRepository.deleteById(id);
    }
}
