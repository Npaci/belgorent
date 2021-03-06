package com.pngabo.belgorent.controllers;

import com.pngabo.belgorent.exceptions.*;
import com.pngabo.belgorent.models.dtos.ErrorDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;
import java.util.Locale;

@ControllerAdvice
public class ControllerAdviser extends ResponseEntityExceptionHandler {
//    Dans un controller
//    @ExceptionHandler(ElementNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorDTO handle(ElementNotFoundException ex) {
//        return new ErrorDTO(ex.getMessage());
//    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ErrorDTO> handle(ElementNotFoundException ex) {
        HttpHeaders headers = new HttpHeaders();//exemple
        headers.add("Expires", Instant.now().plusMillis(600000).toString());
        return  ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(new ErrorDTO(ex.getMessage()));
        // ou return new ResponseEntity<>(new ErrorDTO(ex.getMessage()), headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ElementAlreadyExistException.class)
    public ResponseEntity<ErrorDTO> handle(ElementAlreadyExistException ex) {

        return  ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO(ex.getMessage()));
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<ErrorDTO> handle(AuthenticationCredentialsNotFoundException ex) {
        return  ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorDTO(ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDTO> handle(BadCredentialsException ex) {
        return  ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorDTO("Identifiant ou mot de passe incorrect"));
    }

    @ExceptionHandler(UsernameAlreadyExist.class)
    public ResponseEntity<ErrorDTO> handle(UsernameAlreadyExist ex) {

        return  ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO(ex.getMessage()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDTO> handle(UsernameNotFoundException ex) {
        return  ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO(ex.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDTO> handle(AuthenticationException ex) {
        return  ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO(ex.getMessage()));
    }

    @ExceptionHandler(ImageTooLargeException.class)
    public ResponseEntity<ErrorDTO> handle(ImageTooLargeException ex) {
        return  ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO(ex.getMessage()));
    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        headers.set("message", "L'utilisateur doit ??tre n?? avant aujourd'hui");
//        return super.handleMethodArgumentNotValid(ex, headers, status, request);
//    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<ErrorDTO> handle(InvalidDateException ex) {
        return  ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO(ex.getMessage()));
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorDTO> handle(MethodArgumentNotValidException ex) {
//        String msg = ex.getMessage().toUpperCase();
//        System.out.println(">>>>>>>>>>>>>> "+msg);
//        if (msg.contains("must be a date".toUpperCase()))
//            msg = "Date non valide!";
//        return  ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(new ErrorDTO(msg));
//    }
}
