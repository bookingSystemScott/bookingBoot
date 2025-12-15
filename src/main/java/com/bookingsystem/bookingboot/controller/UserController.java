package com.bookingsystem.bookingboot.controller;

import com.bookingsystem.bookingboot.entity.Users;
import com.bookingsystem.bookingboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Users> getAllUserInfo() throws Exception {
        try{
            System.out.println("24."+userService.getAllUser());
            System.out.println("25."+userService.getAllUser());
            System.out.println("26."+userService.getAllUser());
            System.out.println("27."+userService.getAllUser());
            return userService.getAllUser();
        }catch (Exception e){
            throw  new Exception(e);
        }
    }
}
