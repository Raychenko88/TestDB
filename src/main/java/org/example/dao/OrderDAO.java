package org.example.dao;



import org.example.model.Item;
import org.example.model.Order;
import org.example.model.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OrderDAO {

    private static Logger logger = Logger.getLogger(OrderDAO.class.getName());

    public static Order create(Order order){
        String sql = "INSERT INTO orders(item_id, cart_id, amount) VALUES(?,?,?)";
        String sequenceSql = "SELECT currval(pg_get_serial_sequence('orders','id'))";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             PreparedStatement seqStatement = connection.prepareStatement(sequenceSql)
             ){

            preparedStatement.setInt(1, order.getItemId());
            preparedStatement.setInt(3, order.getAmount());
            preparedStatement.setInt(2, order.getCartId());

            preparedStatement.executeUpdate();

            ResultSet resultSet = seqStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                order.setId(id);

                return order;
            }
        } catch (SQLException e){
            logger.severe(e.getMessage());
        }

        return null;
    }

    public static Order update(Order order){
        String sql = "UPDATE orders SET item_id=?, cart_Id=?, amount=? WHERE id=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            preparedStatement.setInt(1, order.getItemId());
            preparedStatement.setInt(2, order.getCartId());
            preparedStatement.setInt(3, order.getAmount());
            preparedStatement.setInt(4, order.getId());

            preparedStatement.executeUpdate();
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Order findById(Integer id){
        String statement = "SELECT * FROM orders WHERE id=?";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(statement)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();

                order.setId(resultSet.getInt("id"));
                order.setItemId(resultSet.getInt("item_id"));
                order.setAmount(resultSet.getInt("amount"));
                order.setCartId(resultSet.getInt("cart_id"));

                return order;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Order> findByCart(Integer cartId){
        List<Order> orders = new ArrayList<>();
        String statement = "SELECT * FROM orders WHERE cart_id=?";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(statement)) {

            preparedStatement.setInt(1, cartId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();

                order.setId(resultSet.getInt("id"));
                order.setItemId(resultSet.getInt("item_id"));
                order.setAmount(resultSet.getInt("amount"));
                order.setCartId(resultSet.getInt("cart_id"));

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }



    public static void delete(Integer id){
        String statement = "DELETE FROM orders WHERE id=?";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(statement)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static Integer sumAnyUserById(Integer id){
        String sql = "SELECT SUM (i.price * o.amount) AS my_resoult FROM carts c" +
                "JOIN orders o ON c.id = o.cart_id" +
                "JOIN users u ON u.id = c.user_id" +
                "JOIN items i ON i.id = o.item_id" +
                "WHERE u.id = ?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer result = resultSet.getInt("my_resoult");
                return  result;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
