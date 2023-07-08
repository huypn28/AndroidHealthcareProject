package com.example.androidhealthcareproject.user_manager.services;


import com.example.androidhealthcareproject.user_manager.models.Cart;
import com.example.androidhealthcareproject.user_manager.models.Orderplace;
import com.example.androidhealthcareproject.user_manager.models.User;
import com.example.androidhealthcareproject.user_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public  int registerNewUserServiceMethod(String username, String email, String password){
        return userRepository.registerNewUser(username, email, password);
    }

    public  int registerNewCart(int user_id,String product, float price, String otype){
        return userRepository.registerNewCart(user_id, product, price, otype);
    }

    public  int removeCart(int user_id, String otype){
        return userRepository.removeCart(user_id,otype);
    }
    public  int registerNewOrderplace(int user_id, String fullname, String address, String contact, int age, String date, String time, Float amount, String otype){
        return userRepository.registerNewOrderplace(user_id,fullname,address,contact,age, date, time,amount,otype);
    }

    public List<String> checkUserUsername(String username){
        return userRepository.checkUserUsername(username);
    }

    public String checkUserPasswordByUsername(String username){
        return userRepository.checkUserPasswordByUsername(username);
    }
    public User getUserDetailsByUsername(String username){
        return userRepository.getUserDetailsByUsername(username);
    }
    public List<Object[]>  getCartDetailsByUsername(int user_id,String otype){
        return userRepository.getCartDetailsByUsername(user_id,otype);
    }
    public List<Object[]> getOrderplaceDetailsByUsername(int user_id){
        return userRepository.getOrderplaceDetailsByUsername(user_id);
    }

    public Integer getUserIdByUsername(String username){
        return userRepository.getUserIdByUsername(username);
    }

    public  List<String> checkCartUser_id(int user_id){
        return userRepository.checkCartUser_id(user_id);
    }

}
