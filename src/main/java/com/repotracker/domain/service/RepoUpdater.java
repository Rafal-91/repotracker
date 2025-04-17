package com.repotracker.domain.service;

import com.repotracker.domain.model.Repo;
import com.repotracker.domain.repository.RepoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class RepoUpdater {

    private final RepoRepository repoRepository;
    private final RepoRetriever repoRetriever;


    @Transactional
    public void updateRepoById(Long id, Repo repo){
        repoRetriever.findRepoById(id);
        repoRepository.updateById(id, repo);
        log.info("updating repo with id " + id);
    }
}
