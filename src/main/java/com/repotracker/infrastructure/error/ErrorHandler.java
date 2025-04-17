package com.repotracker.infrastructure.error;

import com.repotracker.domain.model.RepoNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException ex) {
        log.warn("UserNotFoundException while accessing user");
        ErrorResponseDto response = new ErrorResponseDto(ex.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @ExceptionHandler(RepoNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(RepoNotFoundException ex) {
        log.warn("RepoNotFoundException while accessing repo");
        ErrorResponseDto response = new ErrorResponseDto(ex.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpMediaTypeException() {
        log.warn("error with http media type");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponseDto("invalid http media type", HttpStatus.NOT_ACCEPTABLE));
    }

}
