package com.repotracker.domain.repository;

import com.repotracker.domain.model.Repo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepoRepository extends Repository<Repo, Long> {

    List<Repo> saveAll(Iterable<Repo> repos);

    List<Repo> findAll();

    Repo save(Repo repo);



}
