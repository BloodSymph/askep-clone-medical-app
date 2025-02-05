package com.askep.medpersonal.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "users.service.store")
public class PropertyConfig {

    private String filePath;

}
