package com.supreme.shoekream.config;

import com.supreme.shoekream.model.entity.Member;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

public class DataRestConfig {
    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer(){
        return RepositoryRestConfigurer.withConfig((config, cors)->
                config.exposeIdsFor(Member.class)
        );
    }
}
