package org.example.dao;

import org.example.model.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOTest {

    @Test
    void create() {
        Order order = new Order(0, 123, 13);
        OrderDAO.create(order);
        assertEquals(order.getCartId(), 13);
    }

    @Test
    void update() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByCart() {
    }
}