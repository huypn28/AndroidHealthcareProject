package com.example.androidhealthcareproject.user_manager.rest_controllers;


import com.example.androidhealthcareproject.user_manager.models.Login;
import com.example.androidhealthcareproject.user_manager.models.User;
import com.example.androidhealthcareproject.user_manager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/v1")
@RequestMapping(value = "/api/v1", method = RequestMethod.GET)

public class LoginApiController {

    @Autowired
    UserService userService;
    @PostMapping("/user/login")
    public ResponseEntity authenticateUser(@RequestBody Login login){

        List<String> userUsername= userService.checkUserUsername(login.getUsername());

        if(userUsername.isEmpty()||userUsername==null){
            return new ResponseEntity("Không tìm thấy tên đăng nhập", HttpStatus.NOT_FOUND);
        }

        String hashed_password=userService.checkUserPasswordByUsername(login.getUsername());

        if (!BCrypt.checkpw(login.getPassword(),hashed_password)){
            return new ResponseEntity("Tên đăng nhập hoặc mật khẩu không chính xác", HttpStatus.BAD_REQUEST);
        }

        User user=userService.getUserDetailsByUsername(login.getUsername());
        return  new ResponseEntity(user, HttpStatus.OK);
    }

}
