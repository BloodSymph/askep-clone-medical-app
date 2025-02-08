package com.askep.patient.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableFeignClients
public class WebMvcConfig {

    @Bean
    public RestTemplate restTemplateConfig() {
        return new RestTemplate();
    }

}
