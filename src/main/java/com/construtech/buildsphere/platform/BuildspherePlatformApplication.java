package com.construtech.buildsphere.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BuildspherePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuildspherePlatformApplication.class, args);
    }

}
