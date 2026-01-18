package com.bookingsystem.bookingboot.security.service;

import com.bookingsystem.bookingboot.security.principal.MyUserPrincipal;
import com.bookingsystem.bookingboot.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Autowired
    private AuthenticationManager authenticationManager;

    public Map auth(Map<String, Object> reqDTO){
        //回傳資訊
        Map<String,String> responseToken = new HashMap<>();
        String username = (String) reqDTO.get("username");
        String password = (String) reqDTO.get("password");
        //使用DaoAuthentication認證
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(username, password);
        authentication = authenticationManager.authenticate(authentication);
        //使用認證過後的資訊產生toekn
        String accessToken = JwtUtil.generateToken(authentication);
        String refreshToken = JwtUtil.generateRefreshToken(authentication);
        responseToken.put("accessToken",accessToken);
        responseToken.put("refreshToken",refreshToken);

        return responseToken;
    }


    public String refreshAccessToken(String refreshToken) throws Exception {
        String newAccessToken = JwtUtil.refreshAccessToken(refreshToken);
        return newAccessToken;
    }
}
