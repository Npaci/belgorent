package com.pngabo.belgorent.controllers;

import com.pngabo.belgorent.exceptions.ElementAlreadyExistException;
import com.pngabo.belgorent.exceptions.ElementNotFoundException;
import com.pngabo.belgorent.exceptions.ImageTooLargeException;
import com.pngabo.belgorent.exceptions.InvalidDateException;
import com.pngabo.belgorent.models.dtos.ErrorDTO;
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

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;

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
                .body(new ErrorDTO(ex.getMessage()));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handle(SQLIntegrityConstraintViolationException ex) {
        String msg = ex.getMessage();
        System.out.println(">>>>>>>>>>>>>> "+msg);
        if (msg.contains("ON PUBLIC.UTILISATEUR(USERNAME)"))
            msg = "Ce nom d'utilisateur existe déjà!";
        else
            System.out.println("L'ERREUR NE CONTIENT PAS CE QUE L'ON CHERCHE!");

        return  ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO(msg));
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
//        headers.set("message", "L'utilisateur doit être né avant aujourd'hui");
//        return super.handleMethodArgumentNotValid(ex, headers, status, request);
//    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<ErrorDTO> handle(InvalidDateException ex) {
        return  ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO(ex.getMessage()));
    }
}
