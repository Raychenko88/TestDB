package org.example.dao;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.example.model.Order;
import org.example.model.User;
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
        Order order = new Order(5,2,1, 4);
        OrderDAO.update(order);
    }

    @Test
    void findById() {
        assertEquals(5,OrderDAO.findById(5).getId());
    }

    @Test
    void findByCart() {
        assertEquals(2,OrderDAO.findByCart(1).size());
    }

    @Test
    void testCreate() {
        Order order = new Order(3,1,48);
        OrderDAO.create(order);
        assertEquals(9, order.getId());
    }

    @Test
    void testUpdate() {
        Order order = new Order(2,1,35);
        order.setId(8);
        OrderDAO.update(order);
        assertEquals(35, order.getAmount());
    }

    @Test
    void testFindById() {
        assertEquals(7,OrderDAO.findById(7).getId());
    }

    @Test
    void testFindByCart() {
        assertEquals(4,OrderDAO.findByCart(1).size());
    }


    @Test
    void sumAnyUserById() {
        assertEquals(10247, OrderDAO.SumAnyUserById(6));
    }
}