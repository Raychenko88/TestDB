package org.example.dao;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.example.model.Cart;
import org.example.model.Item;
import org.example.model.Order;
import org.example.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOTest {

    private static User user;
    private static Cart cart;
    private static Item item;
    private static long currentTime = new Date().getTime();
    private List<Order> testOrders = new ArrayList<>();
    private List<Cart> testCarts = new ArrayList<>();
    private List<User> testUsers = new ArrayList<>();
    private List<Item> testItems = new ArrayList<>();

    @BeforeAll
    public static void setData(){
        user = new User("TestLog", "TestPass", "fName", "lName");
        UserDAO.create(user);
        cart = new Cart(new Date().getTime(), 0, user.getId());
        CartDAO.create(cart);
        item = new Item("EEtestIcode","Tname", 2000, 2);
        ItemDAO.create(item);
    }

    @AfterAll
    public static void deleteData(){
        CartDAO.delete(cart);
        UserDAO.delete(user.getId());
        ItemDAO.delete(item);
    }

    @AfterEach
    public void deleteTempData(){
        testCarts.forEach(CartDAO::delete);
        testUsers.forEach(user -> UserDAO.delete(user.getId()));
        testItems.forEach(ItemDAO::delete);
        testCarts.clear();
        testUsers.clear();
        testItems.clear();
    }


    @Test
    void create() {
        Order order = new Order(item.getId(),cart.getId(),2);
        OrderDAO.create(order);
        testOrders.add(order);
        assertNotNull(order);
        assertNotNull(order.getId());
        OrderDAO.delete(order.getId());
    }

    @Test
    void update() {
        Order order = new Order(item.getId(),cart.getId(),2);
        OrderDAO.create(order);
        testOrders.add(order);
        order.setAmount(3);
        OrderDAO.update(order);
        assertEquals(3,OrderDAO.findById(order.getId()).getAmount());
        OrderDAO.delete(order.getId());
    }

    @Test
    void findById() {
        Order order = new Order(item.getId(),cart.getId(),2);
        OrderDAO.create(order);
        testOrders.add(order);
        assertEquals(order.getId(), OrderDAO.findById(order.getId()).getId());
        OrderDAO.delete(order.getId());
    }

    @Test
    void findByCart() {
        Order order = new Order(item.getId(),cart.getId(),2);
        OrderDAO.create(order);
        testOrders.add(order);
        List<Order> carts = OrderDAO.findByCart(cart.getId());
        assertTrue(carts.size() >= 1);
        OrderDAO.delete(order.getId());
    }

    @Test
    void sumAnyUserById() {
        User user1 = new User("TestLogt1", "TestPasst1", "fNamet1", "lNamet1");
        UserDAO.create(user1);
        Cart cart1 = new Cart(new Date().getTime(), 1, user1.getId());
        CartDAO.create(cart1);
        Order order1 = new Order(item.getId(),cart.getId(),2);
        OrderDAO.create(order1);
        Order order2 = new Order(item.getId(),cart1.getId(),2);
        OrderDAO.create(order2);
        testOrders.add(order1);
        testOrders.add(order2);

        assertEquals(4000, OrderDAO.sumAnyUserById(user.getId()));
        assertEquals(4000, OrderDAO.sumAnyUserById(user1.getId()));

        OrderDAO.delete(order1.getId());
        OrderDAO.delete(order2.getId());
        CartDAO.delete(cart1);
        UserDAO.delete(user1.getId());
    }

    @Test
    void findOrderByItem(){
        Order order = new Order(item.getId(),cart.getId(),2);
        OrderDAO.create(order);
        testOrders.add(order);
        assertEquals(order.getId(), OrderDAO.findOrderByItem(item.getId()).getId());
        OrderDAO.delete(order.getId());
    }
}