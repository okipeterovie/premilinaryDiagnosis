package com.example.preliminaryDiagnosis.commons;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateUtils {

    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder builder) {
        return builder.setConnectTimeout(Duration.ofMillis(15000))
                      .setReadTimeout(Duration.ofMillis(15000))
                      .build();
    }
}
