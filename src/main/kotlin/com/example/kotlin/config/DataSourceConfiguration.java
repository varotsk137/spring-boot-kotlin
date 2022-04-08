package com.example.kotlin.config;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean
    MariaDB4jSpringService mariaDB4jSpringService() {
        return new MariaDB4jSpringService();
    }

    @Bean
    @DependsOn("mariaDB4jSpringService")
    DataSource dataSource(MariaDB4jSpringService mariaDB4jSpringService, DataSourceProperties dataSourceProperties) throws ManagedProcessException {

        DBConfigurationBuilder config = mariaDB4jSpringService.getConfiguration();

        return DataSourceBuilder.create()
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword())
                .url(dataSourceProperties.getUrl())
                .driverClassName(dataSourceProperties.getDriverClassName())
                .build();
    }

}
