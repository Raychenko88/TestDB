package org.example.service;

import org.example.dao.UserDAO;
import org.example.model.User;

import java.util.List;

public class UserService {

    public static User create(User user){
   if (user.getLogin().equals(UserService.findByLogin(user).getLogin())){
       return null;
   }else {
       UserDAO.create(user);
       return user;
   }
    }

    public static User update(User user){
        if (UserService.create(user) == null){
            return UserDAO.update(user);
        }else {
            return null;
        }
    }

    public static void delete(User user){
        UserDAO.delete(user.getId());
    }

    public static List<User> findAllUsers(){
        return UserDAO.findAll();
    }

    public static User findById(User user){
        return UserDAO.findById(user.getId());
    }

    public static User findByLogin(User user){
       return UserDAO.findByLogin(user.getLogin());
    }
}
