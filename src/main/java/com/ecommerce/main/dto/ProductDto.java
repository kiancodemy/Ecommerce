package com.ecommerce.main.dto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProductDto(
        @NotBlank(message = "Product name is required")
        String name,

        @NotBlank(message = "Brand is required")
        String brand,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        BigDecimal price,
        String description,

        @NotNull(message = "Inventory is required")
        @Min(value = 0, message = "Inventory cannot be negative")
        Integer inventory) {}
