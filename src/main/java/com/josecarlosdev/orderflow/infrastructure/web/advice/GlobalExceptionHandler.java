package com.josecarlosdev.orderflow.infrastructure.web.advice;

import com.josecarlosdev.orderflow.domain.exception.DomainException;
import com.josecarlosdev.orderflow.domain.exception.InsufficientStockException;
import com.josecarlosdev.orderflow.domain.exception.InvalidOrderStateException;
import com.josecarlosdev.orderflow.domain.exception.ProductNotFounException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFounException.class) //404
    public ProblemDetail handleProductNotFound(ProductNotFounException e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problem.setTitle("Producto no encontrado");
        return problem;
    }
    @ExceptionHandler(InsufficientStockException.class) // 409
    public ProblemDetail handleInsufficientStock(InsufficientStockException e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problem.setTitle("Stock insuficiente");
        return problem;
    }

    @ExceptionHandler(InvalidOrderStateException.class) //409
    public ProblemDetail handleInvalidOrderState(InvalidOrderStateException e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problem.setTitle("Transición de estado inválida");
        return problem;
    }

    @ExceptionHandler(DomainException.class) // 500
    public ProblemDetail handleDomainException(DomainException e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problem.setTitle("Error no previsto "); // por si olvido un handler para un nuevo error
        return problem;
    }
}
