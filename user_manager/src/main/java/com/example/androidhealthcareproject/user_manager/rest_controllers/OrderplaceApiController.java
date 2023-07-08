package com.example.androidhealthcareproject.user_manager.rest_controllers;

import com.example.androidhealthcareproject.user_manager.models.Cart;
import com.example.androidhealthcareproject.user_manager.models.Login;
import com.example.androidhealthcareproject.user_manager.models.Orderplace;
import com.example.androidhealthcareproject.user_manager.models.User;
import com.example.androidhealthcareproject.user_manager.repository.UserRepository;
import com.example.androidhealthcareproject.user_manager.services.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

import static org.springframework.boot.context.properties.source.ConfigurationPropertyName.isValid;

@RestController
//@RequestMapping("/api/v1")
@RequestMapping(value = "/api/v1", method = RequestMethod.GET)

public class OrderplaceApiController {

    @Autowired
    UserService userService;
    // UserRepository userRepository;

    @PostMapping("/user/orderplace/register")
    public ResponseEntity registerNewOrderplace(@RequestParam("username") String username,
                                                @RequestParam("fullname")String fullname,
                                                @RequestParam("address")String address,
                                                @RequestParam("contact")String contact,
                                                @RequestParam("age")int age,
                                                @RequestParam("date")String date,
                                                @RequestParam("time")String time,
                                                @RequestParam("amount")float amount,
                                                @RequestParam("otype")String otype) {
        // Code logic
        List<String> userUsername= userService.checkUserUsername(username);
        if(userUsername.isEmpty()||userUsername==null){
            return new ResponseEntity("Không tìm thấy tên đăng nhập", HttpStatus.NOT_FOUND);
        }
        int user_id= userService.getUserIdByUsername(username);
        int result = userService.registerNewOrderplace(user_id,fullname,address,contact,age, date, time,amount,otype);

        //  List<String> result1 = userRepository.checkUserUsername(username);


        if (result != 1) {
            return new ResponseEntity<>("Thất bại", HttpStatus.BAD_REQUEST);

        } else {
            return new ResponseEntity<>("Thành công", HttpStatus.OK);
        }

    }
    @GetMapping("/user/orderplace/get")
    public ResponseEntity getOrderData(@RequestParam("username") String username) {
        List<String> userUsername= userService.checkUserUsername(username);
        if(userUsername.isEmpty()||userUsername==null){
            return new ResponseEntity("Không tìm thấy tên đăng nhập", HttpStatus.NOT_FOUND);
        }
        int user_id= userService.getUserIdByUsername(username);
        List<Object[]> orderplaces = userService.getOrderplaceDetailsByUsername(user_id);

        if (orderplaces == null) {
            return new ResponseEntity("Không tìm thấy dữ liệu", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(orderplaces, HttpStatus.OK);

    }
}
