package com.example.androidhealthcareproject.user_manager.rest_controllers;

import com.example.androidhealthcareproject.user_manager.models.Cart;
import com.example.androidhealthcareproject.user_manager.models.Login;
import com.example.androidhealthcareproject.user_manager.models.User;
import com.example.androidhealthcareproject.user_manager.repository.UserRepository;
import com.example.androidhealthcareproject.user_manager.services.UserService;
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

public class CartApiController {

    @Autowired
    UserService userService;
    // UserRepository userRepository;

    @PostMapping("/user/cart/register")
    public ResponseEntity registerNewCart(@RequestParam("username") String username,
                                          @RequestParam("product" ) String product,
                                          @RequestParam("price" ) float price,
                                          @RequestParam("otype") String otype) {
        // Code logic
        List<String> userUsername= userService.checkUserUsername(username);
        if(userUsername.isEmpty()||userUsername==null){
            return new ResponseEntity("Không tìm thấy tên đăng nhập", HttpStatus.NOT_FOUND);
        }
        int user_id= userService.getUserIdByUsername(username);
        int result = userService.registerNewCart(user_id, product, price, otype);

        //  List<String> result1 = userRepository.checkUserUsername(username);


        if (result != 1) {
            return new ResponseEntity<>("Thất bại", HttpStatus.BAD_REQUEST);

        } else {
            return new ResponseEntity<>("Thành công", HttpStatus.OK);
        }

    }
    @DeleteMapping("/user/cart/delete")
    public ResponseEntity<String> removeCart(@RequestParam("username") String username,
                                             @RequestParam("otype") String otype) {
        // Code logic
        List<String> userUsername= userService.checkUserUsername(username);
        if(userUsername.isEmpty()||userUsername==null){
            return new ResponseEntity("Không tìm thấy tên đăng nhập", HttpStatus.NOT_FOUND);
        }
        int user_id= userService.getUserIdByUsername(username);
        int result = userService.removeCart(user_id, otype);

        if (result == 0) {
            return new ResponseEntity<>("Không tìm thấy giỏ hàng để xóa", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Đã xóa giỏ hàng thành công", HttpStatus.OK);
        }
    }

    @GetMapping("/user/cart/get")
    public ResponseEntity getCartData(@RequestParam("username") String username, @RequestParam("otype") String otype) {
        // Code logic
        List<String> userUsername= userService.checkUserUsername(username);
        if(userUsername.isEmpty()||userUsername==null){
            return new ResponseEntity("Không tìm thấy tên đăng nhập", HttpStatus.NOT_FOUND);
        }
        int user_id= userService.getUserIdByUsername(username);
        List<Object[]> cartData = userService.getCartDetailsByUsername(user_id,otype);
        if (cartData == null) {
            return new ResponseEntity("Không tìm thấy dữ liệu", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(cartData, HttpStatus.OK);
    }

}
