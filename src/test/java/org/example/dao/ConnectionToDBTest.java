package org.example.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionToDBTest {

    @Test
    void getConnection() {
        Connection connection = ConnectionToDB.getConnection();
        assertNotNull(connection);
    }
}