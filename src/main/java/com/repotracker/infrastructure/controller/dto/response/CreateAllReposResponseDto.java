package com.repotracker.infrastructure.controller.dto.response;

import java.util.List;

public record CreateAllReposResponseDto(List<RepoDto> repos) {
}
