package org.example;

import org.example.dao.CartDAO;
import org.example.dao.ItemDAO;
import org.example.dao.OrderDAO;
import org.example.dao.UserDAO;
import org.example.model.Cart;
import org.example.model.Item;
import org.example.model.Order;
import org.example.model.User;

import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        Order order = new Order(2,1,2);
        order.setAmount(23);
        OrderDAO.update(order);
    }
}
