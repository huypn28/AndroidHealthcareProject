package com.example.androidhealthcareproject.user_manager.rest_controllers;

import com.example.androidhealthcareproject.user_manager.models.Login;
import com.example.androidhealthcareproject.user_manager.repository.UserRepository;
import com.example.androidhealthcareproject.user_manager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

import static org.springframework.boot.context.properties.source.ConfigurationPropertyName.isValid;

@RestController
//@RequestMapping("/api/v1")
@RequestMapping(value = "/api/v1", method = RequestMethod.GET)

public class RegisterApiController {

    @Autowired
    UserService userService;
    UserRepository userRepository;

    @PostMapping("/user/register")
    public ResponseEntity registerNewUser(@RequestParam("username") String username,
                                          @RequestParam("email") String email,
                                          @RequestParam("password") String password) {

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Nhập đầy đủ thông tin", HttpStatus.BAD_REQUEST);
        }

        // Kiểm tra tên người dùng trùng lặp
        List<String> existingUsernames = userService.checkUserUsername(username);
        if (!existingUsernames.isEmpty()) {
            return new ResponseEntity<>("Tài khoản đã tồn tại", HttpStatus.BAD_REQUEST);
        }

        //Ma hoa va bam mat khau
        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

         int result = userService.registerNewUserServiceMethod(username, email, hashed_password);


                                              if (result != 1) {
           return new ResponseEntity<>("Tài khoản đã tồn tại", HttpStatus.BAD_REQUEST);

        } else {
            return new ResponseEntity<>("Đăng kí thành công", HttpStatus.OK);
        }

    }

}
