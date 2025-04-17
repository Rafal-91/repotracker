package com.repotracker.infrastructure.controller.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateRepoRequestDto(
        @NotNull(message = "owner must not be null")
        @NotEmpty(message = "owner must not be empty")
        String owner,
        @NotNull(message = "repo name must not be null")
        @NotEmpty(message = "repo name must not be empty")
        String name) {
}
