package com.example.lab;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LabApplication {

    public static void main(String[] args) {

        SpringApplication.run(LabApplication.class, args);
    }


/*
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=none
spring.liquibase.change-log=classpath:/db/changelog/changelog-master.sql

     <dependency>
            <groupId>org.liquibase</groupId>
            <artifactId>liquibase-core</artifactId>
        </dependency>
        import liquibase.integration.spring.SpringLiquibase;

 */
    }


