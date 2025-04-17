package com.repotracker.domain.repository;

import com.repotracker.domain.model.Repo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface RepoRepository extends Repository<Repo, Long> {

    List<Repo> findAll(Pageable pageable);

    Optional<Repo> findRepoById(Long id);

    Repo save(Repo repo);

    boolean existsByName(String name);

    void deleteById(Long id);

    @Modifying
    @Query("UPDATE Repo r SET r.owner = :#{#newRepo.owner}, r.name = :#{#newRepo.name} WHERE r.id = :id")
    void updateById(Long id, Repo newRepo);

}
