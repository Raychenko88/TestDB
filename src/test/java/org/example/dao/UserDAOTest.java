package org.example.dao;

import org.example.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    @Test
    void create() {
        User user = new User("Boroda","123","Ivan","Sidorov");
        UserDAO.create(user);
        assertNotNull(user.getId());
    }

    @Test
    void update() {
        User user = new User("BorodaDB","1234","Ivanidze","Sidorovidze");
        user.setId(6);
        UserDAO.update(user);
        assertEquals(user.getLogin(), "BorodaDB");

    }

    @Test
    void findById() {
        UserDAO.findById(6);
        assertEquals("BorodaDB", "BorodaDB");
    }

    @Test
    void findByLogin() {
        UserDAO.findByLogin("BorodaDB");
        assertEquals("1234", "1234");
    }

    @Test
    void findAll() {
       assertNotNull(UserDAO.findAll());
    }
}