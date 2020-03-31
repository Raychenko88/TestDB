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
    public static void setData() {
        user = new User("testLogin", "testPass", "fName", "lName");
        UserDAO.create(user);
    }

    @AfterAll
    public static void deleteData() {
        UserDAO.delete(user.getId());
    }

    @AfterEach
    public void deleteTempData() {
        testCarts.forEach(cart -> CartDAO.delete(cart));
        testCarts.clear();
    }

    @Test
    void create() {
        Cart cart = new Cart(currentTime, 1, user.getId());
        CartDAO.create(cart);
        testCarts.add(cart);
        assertNotNull(cart);
        assertNotNull(cart.getId());
    }

    @Test
    void update() {
        Cart cart = new Cart(currentTime, 1, user.getId());
        CartDAO.create(cart);
        testCarts.add(cart);
        cart.setClosed(0);
        CartDAO.update(cart);
        assertEquals(0, CartDAO.findById(cart.getId()).getClosed());
    }

    @Test
    void findById() {
        Cart cart = new Cart(currentTime, 1, user.getId());
        CartDAO.create(cart);
        testCarts.add(cart);
        assertNotNull(CartDAO.findById(cart.getId()));
    }

    @Test
    void findByUser() {
        Cart cart = new Cart(currentTime, 1, user.getId());
        CartDAO.create(cart);
        testCarts.add(cart);

        List<Cart> carts = CartDAO.findByUser(user);
        assertFalse(carts.isEmpty());
        assertEquals(1, carts.size());
    }

    @Test
    void findOpenCartByUser() {
        Cart cart1 = new Cart(1L, 1, user.getId());
        Cart cart2 = new Cart(0L, 0, user.getId());
        CartDAO.create(cart1);
        CartDAO.create(cart2);
        testCarts.add(cart1);
        testCarts.add(cart2);

        Cart cart = CartDAO.findOpenCartByUser(user.getId());
        assertNotNull(cart);
        assertEquals(0L, cart.getCreationTime());
    }

}