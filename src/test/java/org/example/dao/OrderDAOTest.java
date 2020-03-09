package org.example.dao;

import org.example.model.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOTest {

    @Test
    void create() {
        Order order = new Order(2,1,2);
        OrderDAO.create(order);
        assertEquals(order.getCartId(), 1);
    }

    @Test
    void update() {
        Order order = new Order(2,1,23);
        OrderDAO.update(order);
    }

    @Test
    void findById() {
    }

    @Test
    void findByCart() {
    }

    @Test
    void testCreate() {
    }

    @Test
    void testUpdate() {
    }

    @Test
    void testFindById() {
    }

    @Test
    void testFindByCart() {
    }

    @Test
    void findClosedOrdersByUserAndPeriod() {
    }
}