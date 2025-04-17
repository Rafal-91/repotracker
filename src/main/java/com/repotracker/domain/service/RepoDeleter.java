package com.repotracker.domain.service;

import com.repotracker.domain.repository.RepoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class RepoDeleter {

    private final RepoRepository repoRepository;
    private final RepoRetriever repoRetriever;

    public void deleteById(Long id) {
        repoRetriever.findRepoById(id);
        repoRepository.deleteById(id);
        log.info("deleting repo with id " + id);
    }
}
