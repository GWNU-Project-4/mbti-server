package com.mbti.server.mbti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class MbtiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbtiApplication.class, args);
    }

}
