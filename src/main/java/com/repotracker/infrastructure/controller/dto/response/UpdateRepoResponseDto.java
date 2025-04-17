package com.repotracker.infrastructure.controller.dto.response;

import org.springframework.http.HttpStatus;

public record UpdateRepoResponseDto(String message, HttpStatus status) {
}
