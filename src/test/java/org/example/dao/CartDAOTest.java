package org.example.dao;

import org.example.model.Cart;
import org.example.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartDAOTest {

    private static User user;
    private static long currentTime = new Date().getTime();
    private List<Cart> testCarts = new ArrayList<>();

    @BeforeAll
    public static void setData(){
        User user = new User("TestLog", "TestPass", "fName", "lName");
        UserDAO.create(user);
    }

    @AfterAll
    public static void deleteData(){
        UserDAO.delete(user.getId());
    }

    @AfterEach
    public void deleteTempData(){
        testCarts.forEach(cart -> CartDAO.delete(cart));
        testCarts.clear();
    }

    @Test
    void create() {
        Cart cart = new Cart(currentTime,1,user.getId());
        CartDAO.create(cart);
        testCarts.add(cart);
        assertNotNull(cart);
        assertNotNull(cart.getId());
    }

    @Test
    void update() {
        Cart cart = new Cart(currentTime,1,user.getId());
        CartDAO.create(cart);
        testCarts.add(cart);
        cart.setClosed(0);
        CartDAO.update(cart);
        assertEquals(0, CartDAO.findById(cart.getId()));
    }

    @Test
    void findById() {
        Cart cart = new Cart(currentTime,1,user.getId());
        CartDAO.create(cart);
        testCarts.add(cart);
        assertNotNull(CartDAO.findById(cart.getId()));
    }

    @Test
    void findByUser() {
        Cart cart = new Cart(currentTime,1,user.getId());
        CartDAO.create(cart);
        testCarts.add(cart);
        List<Cart> carts = CartDAO.findByUser(user);
        assertFalse(carts.isEmpty());

    }
}