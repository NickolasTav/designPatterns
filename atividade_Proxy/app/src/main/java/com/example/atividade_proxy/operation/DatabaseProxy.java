package com.example.proxydb.operation;

import java.sql.Connection;

public class DatabaseProxy implements IDatabaseOperation {

    private final RealDatabaseOperation real;
    private final Connection conn;

    public DatabaseProxy(Connection conn) {
        this.conn = conn;
        this.real = new RealDatabaseOperation(conn);
    }

    @Override
    public void insertUser(String name, String email) {
        try {
            // validações
            if (name == null || name.isBlank() || email == null || email.isBlank()) {
                throw new IllegalArgumentException("Nome e e‑mail não podem ser vazios.");
            }
            // chamada real
            real.insertUser(name, email);
            conn.commit();
            System.out.println("Usuário inserido: " + name);
        } catch (Exception e) {
            try {
                conn.rollback();
                System.err.println("Rollback realizado por erro: " + e.getMessage());
            } catch (Exception exRollback) {
                System.err.println("Erro no rollback: " + exRollback.getMessage());
            }
            throw new RuntimeException("Erro no proxy: " + e.getMessage(), e);
        }
    }
}
