package com.test.task.products.service;

import com.test.task.products.controller.dto.ProductDTO;
import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    ProductDTO getProduct(Integer id);

    ProductDTO createProduct(ProductDTO dto);

    ProductDTO updateProduct(Integer id, ProductDTO dto);

    void deleteProduct(Integer id);
}
