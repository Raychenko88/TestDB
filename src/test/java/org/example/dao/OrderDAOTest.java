package org.example.dao;

import org.example.model.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOTest {

    //TODO create two users and two carts for every user, save all objects to DB
    //TODO delete all test users and carts after all tests
    //TODO delete temp data(orders) after each test

    @Test
    void create() {
        Order order = new Order(4,2,2);
        OrderDAO.create(order);
        assertEquals(2,order.getCartId());
    }

    @Test
    void update() {
        Order order = new Order(5,2,1, 4);
        OrderDAO.update(order);
    }

    @Test
    void findById() {
        assertEquals(5,OrderDAO.findById(5).getId());
    }

    @Test
    void findByCart() {
        //TODO use both carts and users
        assertEquals(2,OrderDAO.findByCart(1).size());
    }

    @Test
    void testFindById() {
        assertEquals(7,OrderDAO.findById(7).getId());
    }

    @Test
    void sumAnyUserById() {
        //TODO use both carts and users
        assertEquals(10247, OrderDAO.sumAnyUserById(6));
    }
}