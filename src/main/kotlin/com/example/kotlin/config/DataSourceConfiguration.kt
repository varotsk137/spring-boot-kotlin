package com.example.kotlin.config

import ch.vorburger.mariadb4j.DBConfigurationBuilder
import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import javax.sql.DataSource

@Configuration
class DataSourceConfiguration {

    @Bean("mariaDB4jSpringService")
    fun mariaDb4jSpringService(): MariaDB4jSpringService = MariaDB4jSpringService()

    @Bean
    @DependsOn("mariaDB4jSpringService")
    fun dataSource(mariaDB4jSpringService: MariaDB4jSpringService, dataSourceProperties: DataSourceProperties): DataSource {

        val config: DBConfigurationBuilder = mariaDB4jSpringService.configuration

        return DataSourceBuilder.create()
            .username(dataSourceProperties.username)
            .password(dataSourceProperties.password)
            .url(dataSourceProperties.url)
            .driverClassName(dataSourceProperties.driverClassName)
            .build()

    }

}