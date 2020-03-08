package org.example;

import org.example.dao.UserDAO;
import org.example.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<User> users = UserDAO.findAll();
        for (User a : users){
            System.out.println(a.getId());
            System.out.println(a.getLogin());
            System.out.println(a.getPassword());
            System.out.println(a.getFirstName());
            System.out.println(a.getLastName());
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        }
    }
}
