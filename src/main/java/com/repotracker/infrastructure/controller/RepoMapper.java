package com.repotracker.infrastructure.controller;

import com.repotracker.domain.model.Repo;
import com.repotracker.infrastructure.client.dto.GetAllReposResponseClientDto;
import com.repotracker.infrastructure.controller.dto.request.UpdateRepoRequestDto;
import com.repotracker.infrastructure.controller.dto.response.*;
import com.repotracker.infrastructure.controller.dto.request.CreateRepoRequestDto;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.stream.Collectors;

public class RepoMapper {

    public static RepoDto mapFromRepoToRepoDto(Repo repo) {
        return new RepoDto(repo.getId(), repo.getOwner(), repo.getName());
    }

    public static GetRepoResponseDto mapFromRepoToGetRepoResponseDto(Repo repo) {
        return new GetRepoResponseDto(RepoMapper.mapFromRepoToRepoDto(repo));
    }

    public static GetAllReposResponseDto mapFromRepoToGetAllReposResponseDto(List<Repo> repos) {
        List<RepoDto> listRepoDto = repos
                .stream()
                .map(RepoMapper::mapFromRepoToRepoDto)
                .toList();
        return new GetAllReposResponseDto(listRepoDto);
    }

    public static Repo mapFromCreateRepoRequestDtoToRepo(CreateRepoRequestDto createRepoRequestDto) {
        return new Repo(createRepoRequestDto.owner(), createRepoRequestDto.name());
    }

    public static CreateRepoResponseDto mapFromRepoToCreateRepoResponseDto(Repo repo) {
        RepoDto savedRepo = mapFromRepoToRepoDto(repo);
        return new CreateRepoResponseDto(savedRepo);
    }

    public static List<Repo> mapFromGetAllReposResponseClientDtoToRepo(List<GetAllReposResponseClientDto> reposDto) {
        return reposDto
                .stream()
                .map(repo -> new Repo(repo.owner().login(), repo.name()))
                .collect(Collectors.toList());
    }

    public static CreateAllReposResponseDto mapRepoToCreateAllReposResponseDto(List<Repo> repos) {
        List<RepoDto> repoDtos = repos.stream()
                .map(repo -> new RepoDto(repo.getId(), repo.getName(), repo.getOwner()))
                .collect(Collectors.toList());
        return new CreateAllReposResponseDto(repoDtos);
    }

    public static DeleteRepoResponseDto mapFromRepoToDeleteRepoResponseDto(Long id){
        return new DeleteRepoResponseDto("deleted repo with id: " + id , HttpStatus.OK);
    }

    public static Repo mapFromUpdateRepoRequestDtoToRepo(UpdateRepoRequestDto updateRepoRequestDto) {
        return new Repo(updateRepoRequestDto.owner(), updateRepoRequestDto.name());
    }

    public static UpdateRepoResponseDto mapFromRepoToUpdateRepoResponseDto(Long id) {
        return new UpdateRepoResponseDto("updated repo with id: " + id , HttpStatus.OK);
    }
}
