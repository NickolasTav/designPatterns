package com.example.proxydb.operation;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RealDatabaseOperation implements IDatabaseOperation {
    private final Connection conn;

    public RealDatabaseOperation(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertUser(String name, String email) {
        String sql = "INSERT INTO usuarios(nome, email) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro no RealDatabaseOperation: " + e.getMessage(), e);
        }
    }
}
