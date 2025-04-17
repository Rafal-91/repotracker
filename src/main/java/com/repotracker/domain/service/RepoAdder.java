package com.repotracker.domain.service;

import com.repotracker.domain.model.Repo;
import com.repotracker.domain.repository.RepoRepository;
import com.repotracker.infrastructure.client.GitHubClient;
import com.repotracker.infrastructure.client.dto.GetAllReposResponseClientDto;
import com.repotracker.infrastructure.controller.RepoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class RepoAdder {

    private final GitHubClient gitHubClient;
    private final RepoRepository repoRepository;

    public List<Repo> addAllReposByUserName(String userName) {
        List<GetAllReposResponseClientDto> response = gitHubClient.getOwnerAndName(userName);
        List<Repo> repos = RepoMapper.mapFromGetAllReposResponseClientDtoToRepo(response);
        return saveIfNotExists(repos);
    }

    private List<Repo> saveIfNotExists(List<Repo> repos) {
        List<Repo> savedRepos = new ArrayList<>();
        for (Repo repo : repos) {
            if(!repoRepository.existsByName(repo.getName())) {
                savedRepos.add(repo);
                log.info("adding new repo: " + repo.getOwner() + " " + repo.getName());
                repoRepository.save(repo);
            }
        }
        return savedRepos;
    }

    public Repo addRepo (Repo repo) {
        log.info("adding new repo: " + repo);
        return repoRepository.save(repo);
    }
}
