package org.example.dao;

import org.example.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    @Test
    void create() {
        User user = new User("Qwerty","123","Ivan","Sidorov");
        UserDAO.create(user);
        assertNotNull(user.getId());
    }

    @Test
    void update() {
        User user = new User("Asdfghj","321","Leha","Boroda");
        user.setId(8);
        UserDAO.update(user);
        assertEquals(user.getLogin(), "Asdfghj");

    }

    @Test
    void findById() {
        assertEquals("321", UserDAO.findById(8).getPassword());
    }

    @Test
    void findByLogin() {
        assertEquals("Asdfghj", UserDAO.findByLogin("Asdfghj").getLogin());
    }

    @Test
    void findAll() {
        List<User> users = UserDAO.findAll();

       assertEquals(4,users.size());
    }
}