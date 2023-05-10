package com.test.task.products.service.impl;

import com.test.task.products.controller.dto.ProductDTO;
import com.test.task.products.exception.impl.NotFoundException;
import com.test.task.products.model.Category;
import com.test.task.products.model.Product;
import com.test.task.products.repository.ProductRepository;
import com.test.task.products.service.CategoryService;
import com.test.task.products.service.ProductService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private static final String FORMAT_PRODUCT_NOT_FOUND = "Product with id:%d not found";
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(String.format(FORMAT_PRODUCT_NOT_FOUND, id))
                );
        return toDTO(product);
    }

    @Transactional
    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        Category category = getOrCreateNew(dto.getCategory());

        return toDTO(productRepository.save(toEntity(dto, category)));
    }


    @Transactional
    @Override
    public ProductDTO updateProduct(Integer id, ProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(String.format(FORMAT_PRODUCT_NOT_FOUND, id))
                );

        if (!Objects.equals(dto.getCategory(), product.getCategory().getName())) {
            Category category = getOrCreateNew(dto.getCategory());
            product.setCategory(category);
        }

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImage(dto.getImage());
        product.setInStock(dto.getInStock());

        return toDTO(productRepository.save(product));
    }

    @Transactional
    @Override
    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(String.format(FORMAT_PRODUCT_NOT_FOUND, id))
                );
        productRepository.delete(product);
    }


    private Category getOrCreateNew(String name) {
        Optional<Category> mayCategory = categoryService.getByName(name);
        return mayCategory.orElseGet(() -> categoryService.createCategory(name));
    }


    private ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setImage(product.getImage());
        dto.setCategory(product.getCategory().getName());
        dto.setInStock(product.getInStock());
        return dto;
    }

    private Product toEntity(ProductDTO dto, Category category) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImage(dto.getImage());
        product.setCategory(category);
        product.setInStock(dto.getInStock());
        return product;
    }
}
