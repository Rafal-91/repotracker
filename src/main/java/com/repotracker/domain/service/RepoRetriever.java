package com.repotracker.domain.service;

import com.repotracker.domain.model.Repo;
import com.repotracker.domain.repository.RepoRepository;
import com.repotracker.infrastructure.client.GitHubClient;
import com.repotracker.infrastructure.client.dto.RepositoriesResponseClientDto;
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

    public List<Repo> addAllRepos(String userName) {
        List<RepositoriesResponseClientDto> response = gitHubClient.getOwnerAndName(userName);
        List<Repo> repos = RepoMapper.mapRepositoriesResponseClientDtoToRepo(response);

        return saveRepos(repos);
    }

    private List<Repo> saveRepos(List<Repo> repos) {
        List<Repo> addedRepos = new ArrayList<>();
        for (Repo repo : repos) {
            if(!repoRepository.existsByName(repo.getName())) {
                addedRepos.add(repoRepository.save(repo));
            }
        }
        return addedRepos;
    }
}





