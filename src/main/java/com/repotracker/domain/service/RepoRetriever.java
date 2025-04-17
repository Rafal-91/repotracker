package com.repotracker.domain.service;

import com.repotracker.domain.model.Repo;
import com.repotracker.domain.model.RepoNotFoundException;
import com.repotracker.domain.repository.RepoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class RepoRetriever {

    private final RepoRepository repoRepository;

    public List<Repo> findAll(Pageable pageable){
        log.info("Retrieving all repositories");
        return repoRepository.findAll(pageable);
    }

    public Repo findRepoById(long id) {
        return repoRepository.findRepoById(id)
                .orElseThrow(() -> new RepoNotFoundException("repo with id " + id + " not found"));
    }
}





