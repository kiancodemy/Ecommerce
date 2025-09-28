package com.ecommerce.main.exception;
import com.ecommerce.main.exception.errors.*;
import com.ecommerce.main.reposnse.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        ApiResponse response = new ApiResponse("failed",errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> handleUserNotFound(
            UserNotFoundException ex,
            HttpServletRequest request) {

        ApiResponse apiError=new ApiResponse(ex.getMessage(),null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(AlreadyExist.class)
    public ResponseEntity<ApiResponse> handleUserNotFound(
            AlreadyExist ex,
            HttpServletRequest request) {

        ApiResponse apiError=new ApiResponse(ex.getMessage(),null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ApiResponse> handleUserNotFound(
           ProductNotFound ex,
            HttpServletRequest request) {

        ApiResponse apiError=new ApiResponse(ex.getMessage(),null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ApiResponse> handleUserNotFound(
            FileStorageException  ex,
            HttpServletRequest request) {

        ApiResponse apiError=new ApiResponse(ex.getMessage(),null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }



    @ExceptionHandler(CategoryNotFound.class)
    public ResponseEntity<ApiResponse> handleUserNotFound(
            CategoryNotFound ex,
            HttpServletRequest request) {

        ApiResponse apiError=new ApiResponse(ex.getMessage(),null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }



    //handle other Errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGeneralException(Exception ex) {
        ApiResponse response = new ApiResponse( "internal error", null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
