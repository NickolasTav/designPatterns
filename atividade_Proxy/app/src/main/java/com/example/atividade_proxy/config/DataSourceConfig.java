package com.example.proxydb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public Connection connection() throws SQLException {
        Connection conn = dataSource.getConnection();
        conn.setAutoCommit(false);  // habilita transações manuais
        return conn;
    }
}
