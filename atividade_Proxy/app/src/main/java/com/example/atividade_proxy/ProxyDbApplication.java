package com.example.proxydb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.proxydb.operation.IDatabaseOperation;
import com.example.proxydb.operation.DatabaseProxy;

import java.sql.Connection;

@SpringBootApplication
public class ProxyDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyDbApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(Connection connection) {
        return args -> {
            IDatabaseOperation op = new DatabaseProxy(connection);


            op.insertUser("Níckolas Tavares do Nascimento", "nickolas@gmail.com");
            try {
                op.insertUser("", "invalid@gmail.com");
            } catch (Exception ex) {
                System.out.println("Capturado no runner: " + ex.getMessage());
            }
            System.exit(0);
        };
    }
}
