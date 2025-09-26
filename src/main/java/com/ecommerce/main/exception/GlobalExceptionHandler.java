package com.ecommerce.main.exception;
import com.ecommerce.main.exception.errors.AlreadyExist;
import com.ecommerce.main.exception.errors.CategoryNotFound;
import com.ecommerce.main.exception.errors.ProductNotFound;
import com.ecommerce.main.exception.errors.UserNotFoundException;
import com.ecommerce.main.reposnse.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getField() + ": " + error.getDefaultMessage()).orElse("invalid request!!!");

        ApiResponse response = new ApiResponse(errorMessage ,null);
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
        ApiResponse response = new ApiResponse( ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
