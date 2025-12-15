package com.bookingsystem.bookingboot.service.Impl;

import com.bookingsystem.bookingboot.entity.Users;
import com.bookingsystem.bookingboot.mapper.UserMapper;
import com.bookingsystem.bookingboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Users> getAllUser() {
        System.out.println("18."+userMapper.findAllUsers().size());
        return userMapper.findAllUsers();
    }
}
