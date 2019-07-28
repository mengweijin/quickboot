package com.mwj.mwjwork.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = {"com.mwj.mwjwork"})
public class MwjworkSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MwjworkSampleApplication.class, args);
    }

}
