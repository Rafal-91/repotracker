package com.repotracker.infrastructure.client;

import com.repotracker.infrastructure.config.FeignConfig;
import com.repotracker.infrastructure.client.dto.BranchesResponseClientDto;
import com.repotracker.infrastructure.client.dto.RepositoriesResponseClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "git-hub-client", url = "https://api.github.com", configuration = FeignConfig.class)
public interface GitHubClient {

    @GetMapping("/users/{userName}/repos")
    List<RepositoriesResponseClientDto> getOwnerAndName(@PathVariable("userName") String userName);


    @GetMapping("/repos/{userName}/{repositoryName}/branches")
    List<BranchesResponseClientDto> getBranchesByOwnerAndRepositories(@PathVariable("userName") String userName, @PathVariable("repositoryName") String repositoryName);

}
