package com.repotracker.infrastructure.controller;

import com.repotracker.domain.model.Repo;
import com.repotracker.domain.service.*;
import com.repotracker.infrastructure.controller.dto.request.CreateRepoRequestDto;
import com.repotracker.infrastructure.controller.dto.request.UpdateRepoRequestDto;
import com.repotracker.infrastructure.controller.dto.response.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/repotracker")
@AllArgsConstructor
@Log4j2
@RestController
public class RepoTrackerRestController {

    private final RepoRetriever repoRetriever;
    private final RepoAdder repoAdder;
    private final RepoDeleter repoDeleter;
    private final RepoUpdater repoUpdater;

    @GetMapping()
    public ResponseEntity<GetAllReposResponseDto> getAllRepos() {
        List<Repo> repos = repoRetriever.findAll();
        GetAllReposResponseDto response = RepoMapper.mapFromRepoToGetAllReposResponseDto(repos);
        return ResponseEntity.ok(response);
    }

    @GetMapping({"/id"})
    public ResponseEntity<GetRepoResponseDto> getRepoById(@RequestParam("requestId") long requestId) {
        Repo repo = repoRetriever.findRepoById(requestId);
        GetRepoResponseDto response = RepoMapper.mapFromRepoToGetRepoResponseDto(repo);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RepositoriesResponseDto> addAllReposByUserName(@PathVariable String userName) {
        List<Repo> repos = repoAdder.addAllReposByUserName(userName);
        RepositoriesResponseDto response = RepoMapper.mapRepoToRepositoriesResponseDto(repos);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "")
    public ResponseEntity<CreateRepoResponseDto> addRepo(@RequestBody CreateRepoRequestDto requestDto) {
        Repo repo = RepoMapper.mapFromCreateRepoRequestDtoToRepo(requestDto);
        Repo savedRepo = repoAdder.addRepo(repo);
        CreateRepoResponseDto response = RepoMapper.mapFromRepoToCreateRepoResponseDto(savedRepo);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteRepoResponseDto> deleteRepoByIdUsingPathVariable(@PathVariable Long id) {
        repoDeleter.deleteById(id);
        DeleteRepoResponseDto response = RepoMapper.mapFromRepoToDeleteRepoResponseDto(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UpdateRepoResponseDto> updateRepo(@PathVariable Long id, @RequestBody @Valid UpdateRepoRequestDto requestDto){
        Repo repo = RepoMapper.mapFromUpdateRepoRequestDtoToRepo(requestDto);
        repoUpdater.updateRepoById(id, repo);
        UpdateRepoResponseDto response = RepoMapper.mapFromRepoToUpdateRepoResponseDto(id);
        return ResponseEntity.ok(response);
    }



}
