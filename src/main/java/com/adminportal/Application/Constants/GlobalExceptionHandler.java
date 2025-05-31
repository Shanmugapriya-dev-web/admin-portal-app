package com.adminportal.Application.Constants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.adminportal.Application.model.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	// Handle all uncaught exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<?>> handleAllExceptions(Exception ex, WebRequest request) {
		ApiResponse<?> error = new ApiResponse<>("Internal Server Error", null, 500, request.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Handle a specific custom exception
	@ExceptionHandler(CustomCheckedException.class)
	public ResponseEntity<ApiResponse<?>> handleCustomException(CustomCheckedException ex, WebRequest request) {
		ApiResponse<?> error = new ApiResponse<>(ex.getMessage(), null, 404, request.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	 @ExceptionHandler(NoResourceFoundException.class)
	    public ResponseEntity<String> handleNotFound(NoResourceFoundException ex) {
	        // log or silently ignore
	        return ResponseEntity.notFound().build();
	    }
}
