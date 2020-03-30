package org.example.dao;

import org.example.model.Cart;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartDAOTest {

    @Test
    void create() {
        Cart cart = new Cart((long) 32323232,1,8);
        cart.setId(2);
        assertEquals(2, cart.getId());
    }

    @Test
    void update() {
        Cart cart = new Cart((long) 1212121,1,6);
        cart.setId(1);
        CartDAO.update(cart);
    }

    @Test
    void findById() {
        CartDAO.findById(1);
    }

    @Test
    void findByUser() {
        assertEquals(1,CartDAO.findById(6).getId());

    }
}