package com.repotracker.infrastructure.controller.error;

import org.springframework.http.HttpStatus;

public record ErrorRepoResponseDto(String message, HttpStatus status) {
}
