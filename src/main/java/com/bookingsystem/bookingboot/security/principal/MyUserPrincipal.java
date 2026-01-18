package com.bookingsystem.bookingboot.security.principal;

import ch.qos.logback.core.util.StringUtil;
import com.bookingsystem.bookingboot.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

public class MyUserPrincipal implements UserDetails {

    private Users users;

    public MyUserPrincipal(Users users){
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String auth = users.getAuthority();
        if(auth == null || auth.isEmpty()){
            return List.of();
        }
        return List.of(new SimpleGrantedAuthority(auth));
    }

    @Override
    public String getPassword() {
        return users.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return users.getUserName();
    }

    public String getPhoneNumber(){
        return users.getPhoneNumber();
    }

    public String getEmail(){
        return users.getEmail();
    }
}
