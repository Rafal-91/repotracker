package com.repotracker.infrastructure.controller.error;

import com.repotracker.infrastructure.client.error.UserNotFoundException;
import com.repotracker.infrastructure.controller.RepoRestController;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class RepoErrorHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorRepoResponseDto> handleUserNotFoundException(UserNotFoundException ex) {
        log.warn("UserNotFoundException while accessing user");
        ErrorRepoResponseDto response = new ErrorRepoResponseDto(ex.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    public ResponseEntity<ErrorRepoResponseDto> handleHttpMediaTypeException() {
        log.warn("error with http media type");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorRepoResponseDto("invalid http media type", HttpStatus.NOT_ACCEPTABLE));
    }

}
