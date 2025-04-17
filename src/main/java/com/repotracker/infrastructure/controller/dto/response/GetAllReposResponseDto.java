package com.repotracker.infrastructure.controller;

import com.repotracker.domain.model.Repo;
import com.repotracker.infrastructure.controller.dto.response.RepoDto;

import java.util.List;

public record GetAllReposResponseDto(List<RepoDto> repos) {
}
