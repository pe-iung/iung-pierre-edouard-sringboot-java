package com.openclassrooms.P5.controller;
import com.openclassrooms.P5.exceptions.ConflictException;
import com.openclassrooms.P5.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Collection;

import java.time.OffsetDateTime;
import java.util.Objects;


@Slf4j
@ControllerAdvice
public class ApiExceptionInterceptor extends ResponseEntityExceptionHandler {


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException exception, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        ProblemDetail body = createProblemDetail(exception, HttpStatus.BAD_REQUEST, exception.getMessage(), null, null, request);
        return this.handleExceptionInternal(exception, body, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundExceptionException(NotFoundException exception, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        ProblemDetail body = createProblemDetail(exception, HttpStatus.NOT_FOUND, exception.getMessage(), null, null, request);

        return this.handleExceptionInternal(exception, body, headers, HttpStatus.NOT_FOUND, request);
    }



    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> conflictException(ConflictException exception, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        ProblemDetail body = createProblemDetail(exception, HttpStatus.CONFLICT, exception.getMessage(), null, null, request);

        return this.handleExceptionInternal(exception, body, headers, HttpStatus.CONFLICT, request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String[] args = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(msg -> "Field '" + ((DefaultMessageSourceResolvable) Objects.requireNonNull(msg.getArguments())[0]).getDefaultMessage() + "' : " + msg.getDefaultMessage())
                .toArray(String[]::new);


        String defaultDetail = "Argument not valid";
        ProblemDetail body = this.createProblemDetail(exception, status, defaultDetail, null, args, request);
        return this.handleExceptionInternal(exception, body, headers, status, request);
    }

    @Nullable
    protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var errorList = exception.getAllValidationResults()
                .stream()
                .map(ParameterValidationResult::getResolvableErrors)
                .flatMap(Collection::stream)
                .map(MessageSourceResolvable::getDefaultMessage)
                .toArray(String[]::new);

        String defaultDetail = "Argument not valid";

        ProblemDetail body = this.createProblemDetail(exception, status, defaultDetail, null, errorList, request);
        return this.handleExceptionInternal(exception, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail body = this.createProblemDetail(ex, status, ex.getMessage(), (String)null, (Object[])null, request);
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }





    @Override
    protected ProblemDetail createProblemDetail(Exception exception, HttpStatusCode status, String defaultDetail, @Nullable String detailMessageCode, @Nullable Object[] detailMessageArguments, WebRequest request) {
        if(status.is5xxServerError()) {
            log.error("{} - {}", exception.getClass().getSimpleName(), exception.getMessage());
        } else {
            log.warn("{} - {}", exception.getClass().getSimpleName(), exception.getMessage());
        }

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, defaultDetail);
        problemDetail.setProperty("timestamp", OffsetDateTime.now());
        if (detailMessageCode != null) {
            problemDetail.setProperty("messageCode", detailMessageCode);
        }
        if (detailMessageArguments != null) {
            problemDetail.setProperty("details", detailMessageArguments);
        }

        return problemDetail;
    }

}
