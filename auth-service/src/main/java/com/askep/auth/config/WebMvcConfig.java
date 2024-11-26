package com.askep.auth.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableCaching
public class WebMvcConfig {

    @Bean
    public RestTemplate restTemplateConfig() {
        return new RestTemplate();
    }

}
