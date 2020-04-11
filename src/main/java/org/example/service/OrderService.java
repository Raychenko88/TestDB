package org.example.service;



import org.example.dao.CartDAO;
import org.example.dao.OrderDAO;
import org.example.model.Order;
import org.example.model.User;

import java.util.List;
import java.util.Objects;

public class OrderService {

    public static Order create(Order order, User user){

        if (OrderDAO.findByCart(Objects.requireNonNull(CartDAO.findOpenCartByUser(user.getId())).getId()).isEmpty()){
            OrderDAO.create(order);
            return order;
        }else{
            order.setAmount(Objects.requireNonNull(OrderDAO.findById(order.getId())).getAmount() + order.getAmount());
           return OrderDAO.update(order);
        }
    }

    public static Order update(Order order){
        if (OrderDAO.findById(order.getId()) != null){
            OrderDAO.update(order);
            return order;
        }
        return null;
    }

    public static void delete(Integer id){
        OrderDAO.delete(id);
    }

    public static Order findById(Integer id){
       return OrderDAO.findById(id);
    }

    public static List<Order> findByCart(Integer cartId){
        return OrderDAO.findByCart(cartId);
    }

    public static Integer sumAnyUserById(Integer id){
        return OrderDAO.sumAnyUserById(id);
    }
}
