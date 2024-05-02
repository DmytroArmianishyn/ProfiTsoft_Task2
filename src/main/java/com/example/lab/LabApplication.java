package com.example.lab;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class LabApplication {

    public static void main(String[] args) {

        SpringApplication.run(LabApplication.class, args);
    }


        @Bean
        public SpringLiquibase liquibase(DataSource dataSource) {
            SpringLiquibase liquibase = new SpringLiquibase();
            liquibase.setDataSource(dataSource);
            liquibase.setChangeLog("classpath:/db/changelog/changelog-master.sql");
            return liquibase;
        }
    }


