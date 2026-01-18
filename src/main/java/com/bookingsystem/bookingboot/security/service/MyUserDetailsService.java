package com.bookingsystem.bookingboot.security.service;

import com.bookingsystem.bookingboot.entity.Users;
import com.bookingsystem.bookingboot.security.principal.MyUserPrincipal;
import com.bookingsystem.bookingboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查詢有無此使用者
        Users users =  userService.getUserByUsername(username);

        if(users == null){
            throw new UsernameNotFoundException("找不到該使用者");
        }
        //將查詢出的資料封裝成UserDetail
        return new MyUserPrincipal(users);
    }
}
