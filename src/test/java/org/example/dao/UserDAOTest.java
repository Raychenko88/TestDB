package org.example.dao;

import org.example.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private List<User> testUsers = new ArrayList<>();

    @AfterEach
    public void clearData() {
        testUsers.forEach(user -> UserDAO.delete(user.getId()));
        testUsers.clear();
    }

    @Test
    void create() {
        User user = new User("Qwerty","123","Ivan","Sidorov");
        UserDAO.create(user);
        testUsers.add(user);
        assertNotNull(user.getId());
    }

    @Test
    void update() {
        User user = new User("Asdfghj","321","Leha","Boroda");
        UserDAO.create(user);
        testUsers.add(user);
        user.setFirstName("NewTestName");
        UserDAO.update(user);
        assertEquals("NewTestName", UserDAO.findById(user.getId()).getFirstName());
    }

    @Test
    void findById() {
        User user = new User("Asdfghj","321","Leha","Boroda");
        UserDAO.create(user);
        testUsers.add(user);
        assertNotNull(UserDAO.findById(user.getId()));
    }

    @Test
    void findByLogin() {
        User user = new User("Asdfghj","321","Leha","Boroda");
        UserDAO.create(user);
        testUsers.add(user);
        assertNotNull(UserDAO.findByLogin(user.getLogin()));
    }

    @Test
    void findAll() {
        User user1 = new User("Asdfghj","321","Leha","Boroda");
        User user2 = new User("Asdfghj","321","Leha","Boroda");

        UserDAO.create(user1);
        UserDAO.create(user2);
        testUsers.add(user1);
        testUsers.add(user2);

        List<User> users = UserDAO.findAll();
        assertFalse(users.isEmpty());
        assertTrue(users.size() >= 2);
    }

    @Test
    void delete() {
        User user = new User("Asdfghj","321","Leha","Boroda");
        UserDAO.create(user);
        testUsers.add(user);
        assertNotNull(UserDAO.findById(user.getId()));

        UserDAO.delete(user.getId());
        assertNull(UserDAO.findById(user.getId()));
    }
}