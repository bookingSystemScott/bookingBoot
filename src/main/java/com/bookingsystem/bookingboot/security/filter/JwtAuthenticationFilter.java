package com.bookingsystem.bookingboot.security.filter;

import com.bookingsystem.bookingboot.security.principal.MyUserPrincipal;
import com.bookingsystem.bookingboot.security.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    private static final String HEADER_AUTH = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HEADER_AUTH);
        if (authHeader != null ){
            String accessToken = authHeader.replace("Bearer ", "");
            String username = JwtUtil.parseToken(accessToken);

            MyUserPrincipal myUser = (MyUserPrincipal) myUserDetailsService.loadUserByUsername(username);
            //因為在前面已經認證過了，這裡自己設定為驗證成功(三個參數)
            //把第二個參數設定為null，不要把加密過後的密碼存入
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            username, null, myUser.getAuthorities());
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);



    }
}
