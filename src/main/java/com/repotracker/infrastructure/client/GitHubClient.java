package com.repotracker.infrastructure.client;

import com.repotracker.infrastructure.client.dto.GetAllReposResponseClientDto;
import com.repotracker.infrastructure.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "git-hub-client", url = "https://api.github.com", configuration = FeignConfig.class)
public interface GitHubClient {

    @GetMapping("/users/{userName}/repos?per_page=100")
    List<GetAllReposResponseClientDto> getOwnerAndName(@PathVariable("userName") String userName);

}
