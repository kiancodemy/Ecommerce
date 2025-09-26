package com.ecommerce.main.dto;

import jakarta.validation.constraints.NotBlank;

public record AddCategory(@NotBlank(message ="name should not be blank") String name) {

}
