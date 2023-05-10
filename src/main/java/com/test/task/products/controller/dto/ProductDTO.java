package com.test.task.products.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Сведения о товаре")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

    @Schema(description = "Идентификатор товара", example = "1")
    private Integer id;

    @Schema(description = "Название товара", example = "Apple MacBook Pro 16", required = true)
    private String name;

    @Schema(description = "Описание товара", example = "Ноутбук Apple MacBook Pro A2485, 16.2\", Apple M1 Max 10 core, 10-ядерный, 32ГБ 1ТБ, серый космос")
    private String description;

    @Schema(description = "Цена в рублях", example = "326990.00", required = true)
    private BigDecimal price;

    @Schema(description = "Ссылка на изображение", example = "https://mymarket.com?id=23454-34524-236672-66745734")
    private String image;

    @Schema(description = "Категория товара", example = "Ноутбуки APPLE", required = true)
    private String category;

    @Schema(description = "Наличие в продаже", example = "true", defaultValue = "false")
    private Boolean inStock;

}
