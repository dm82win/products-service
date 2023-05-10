package com.test.task.products.controller;

import com.test.task.products.controller.dto.ApiResponse;
import com.test.task.products.controller.dto.ProductDTO;
import com.test.task.products.service.ProductService;
import com.test.task.products.service.TraceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "Product controller", description = "Сервис для работы с товарами. Класс ProductController")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    private final TraceService traceService;


    @Operation(summary = "Получить информацию о всех товарах")
    @GetMapping("")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }


    @Operation(summary = "Получить информацию о товаре по идентификатору")
    @GetMapping("/{id}")
    public ProductDTO getProduct(
            @Parameter(description = "Идентификатор товара")
            @PathVariable Integer id) {
        return productService.getProduct(id);
    }


    @Operation(summary = "Создать новый товар")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(
            @RequestBody ProductDTO dto) {
        return productService.createProduct(dto);
    }


    @PutMapping("/{id}")
    public ProductDTO updateProduct(
            @Parameter(description = "Идентификатор товара")
            @PathVariable Integer id,
            @RequestBody ProductDTO dto) {
        return productService.updateProduct(id, dto);
    }


    @DeleteMapping("/{id}")
    public ApiResponse deleteProduct(
            @Parameter(description = "Идентификатор товара")
            @PathVariable Integer id) {
        productService.deleteProduct(id);
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message(String.format("Product with id:%d deleted", id))
                .timestamp(LocalDateTime.now())
                .requestId(traceService.getTraceId())
                .build();
    }

}
