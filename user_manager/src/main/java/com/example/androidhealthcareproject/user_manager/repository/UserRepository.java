package com.example.androidhealthcareproject.user_manager.repository;

import com.example.androidhealthcareproject.user_manager.models.Cart;
import com.example.androidhealthcareproject.user_manager.models.Orderplace;
import com.example.androidhealthcareproject.user_manager.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {


    @Query(value = "SELECT username FROM users WHERE username = :username ",nativeQuery = true)
    List<String> checkUserUsername(@Param("username")String username);

    @Query(value = "SELECT password FROM users WHERE username = :username ",nativeQuery = true)
    String checkUserPasswordByUsername(@Param("username")String username);

   @Query(value = "SELECT id FROM users WHERE username = :username ",nativeQuery = true)
    Integer getUserIdByUsername(@Param("username")String username);

    @Query(value = "SELECT user_id FROM cart WHERE user_id = :user_id ",nativeQuery = true)
    List<String> checkCartUser_id(@Param("user_id")int user_id);

    @Query(value = "SELECT * FROM users WHERE username = :username ",nativeQuery = true)
    User getUserDetailsByUsername(@Param("username")String username);

    @Query(value = "SELECT * FROM cart WHERE user_id = :user_id and otype=:otype",nativeQuery = true)
    List<Object[]>  getCartDetailsByUsername(@Param("user_id")int user_id,@Param("otype")String otype);

 @Query(value = "SELECT * FROM orderplace WHERE user_id = :user_id ",nativeQuery = true)
 List<Object[]> getOrderplaceDetailsByUsername(@Param("user_id")int user_id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO USERS(username, email,password) VALUES(:username, :email, :password)",nativeQuery = true)
    int registerNewUser(
            @Param("username")String username,
            @Param("email")String email,
            @Param("password")String password
                        );
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO CART(user_id, product, price,otype) VALUES(:user_id,:product, :price, :otype)",nativeQuery = true)
    int registerNewCart(
            @Param("user_id")int user_id,
            @Param("product")String product,
            @Param("price")float price,
            @Param("otype")String otype
    );

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Orderplace(user_id, fullname ,address,contact,age, date, time,amount,otype) VALUES(:user_id,:fullname,:address,:contact,:age, :date, :time,:amount,:otype)",nativeQuery = true)
    int registerNewOrderplace(
            @Param("user_id")int user_id,
            @Param("fullname")String fullname,
            @Param("address")String address,
            @Param("contact")String contact,
            @Param("age")int age,
            @Param("date")String date,
            @Param("time")String time,
            @Param("amount")float amount,
            @Param("otype")String otype
    );
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM cart WHERE user_id = :user_id AND otype = :otype", nativeQuery = true)
    int removeCart(@Param("user_id") int user_id,
                   @Param("otype") String otype
    );


}
