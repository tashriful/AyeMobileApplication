package com.aye.web.exception;

import com.aye.web.dto.ApiResponse;
import com.aye.web.utill.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle ResourceNotFoundException
    @ExceptionHandler(CustomExceptions.ResourceNotFoundException.class)
    public
    ResponseEntity <ApiResponse<Object>> handleResourceNotFoundException(CustomExceptions.ResourceNotFoundException ex) {
        ApiResponse<Object> errorMessage = ResponseUtils.error(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(ResponseUtils.error(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    // Handle BadRequestException
    @ExceptionHandler(CustomExceptions.BadRequestException.class)
    public ResponseEntity <ApiResponse<Object>> handleBadRequestException(CustomExceptions.BadRequestException ex) {

        ApiResponse<Object> errorMessage = ResponseUtils.error(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GeneralExceptions.class)
    public ResponseEntity <ApiResponse<Object>> handleGeneralException(GeneralExceptions ex) {
        ApiResponse<Object> errorMessage = ResponseUtils.error(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    // Handle all other exceptions (Generic fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity <ApiResponse<Object>> handleGenericException(Exception ex) {
        ApiResponse<Object> errorMessage = ResponseUtils.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity <ApiResponse<Object>> handleIOException(IOException ex) {
        ApiResponse<Object> errorMessage = ResponseUtils.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

