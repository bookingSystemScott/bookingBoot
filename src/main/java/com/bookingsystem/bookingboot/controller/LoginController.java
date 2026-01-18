package com.bookingsystem.bookingboot.controller;


import com.bookingsystem.bookingboot.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public Map getJwtToken(@RequestBody Map<String,String> userInfo){
        Map<String,Object> reqDTO = new HashMap<>();
        reqDTO.put("username",userInfo.get("username"));
        reqDTO.put("password",userInfo.get("password"));
        return  jwtService.auth(reqDTO);
    }

    @PostMapping("/refresh")
    public String getJwtTokenByRefreshToken(@RequestBody Map<String,String> refreshToken) throws Exception {
        String token = (String) refreshToken.get("refreshToken");
        return  jwtService.refreshAccessToken(token);
    }

}
