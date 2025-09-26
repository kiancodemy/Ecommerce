package com.ecommerce.main.exception.errors;

public class CategoryNotFound extends RuntimeException {
    public CategoryNotFound(String message) {
        super(message);
    }

}
