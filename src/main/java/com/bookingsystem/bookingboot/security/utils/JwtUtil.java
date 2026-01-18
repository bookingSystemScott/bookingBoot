package com.bookingsystem.bookingboot.security.utils;

import com.bookingsystem.bookingboot.security.principal.MyUserPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.security.Key;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    private static final String ISS = "Scott";
    private static final String SECRET = "AlohomoraIsASpellUsedToOpenDoors";

    // access token有效時間
    private static final int EXPIRE_TIME = 1;

    // refresh token 有效時間（天）
    private static final int REFRESH_EXPIRE_DAYS = 14;


    // 產生access_token
    public static String generateToken(Authentication authentication){
        MyUserPrincipal myUserPrincipal =  (MyUserPrincipal) authentication.getPrincipal();
        Calendar exp = Calendar.getInstance();
        exp.add(Calendar.MINUTE,EXPIRE_TIME);

        //設定payload
        Claims claims =  Jwts.claims();
        claims.setSubject(myUserPrincipal.getUsername());
        claims.setIssuer(ISS);
        claims.setExpiration(exp.getTime());
        Key secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey)
                .compact(); // 將 JwtBuilder 構建的 JWT 物件，壓縮為一個字串的形式
    }

    /** 產生 Refresh Token（JWT，產生方式依照你給的：subject/issuer/expiration/signWith） */
    public static String generateRefreshToken(Authentication authentication) {

        Key secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());
        MyUserPrincipal myUserPrincipal =  (MyUserPrincipal) authentication.getPrincipal();
        Calendar exp = Calendar.getInstance();
        exp.add(Calendar.DAY_OF_MONTH, REFRESH_EXPIRE_DAYS);

        Claims claims = Jwts.claims();
        claims.setSubject(myUserPrincipal.getUsername()); // ✅ 依照你的方法：sub = username
        claims.setIssuer(ISS);
        claims.setExpiration(exp.getTime());

        // ✅ 用 claim 區分 refresh（很重要）
        claims.put("typ", "refresh");

        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey)
                .compact();
    }

    public static String parseToken(String token){
        Key secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());

        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(secretKey).build();

        Claims claims = parser.parseClaimsJws(token).getBody();
        String username = claims.getSubject();

        return username;
    }

    /** 只靠 username 產生新的 Access Token（refresh 換新 access 會用到） */
    public static String generateAccessTokenByUsername(String username) {

        Key secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());

        Calendar exp = Calendar.getInstance();
        exp.add(Calendar.MINUTE, EXPIRE_TIME);

        Claims claims = Jwts.claims();
        claims.setSubject(username);
        claims.setIssuer(ISS);
        claims.setExpiration(exp.getTime());
        claims.put("typ", "access");

        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey)
                .compact();
    }



    /** 驗證 JWT（驗簽 + issuer + exp），回傳 Claims */
    private static Claims parseAndValidate(String token) throws Exception {
        try {
            Key secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());

            JwtParser parser = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .requireIssuer(ISS) // ✅ 確認 issuer
                    .build();

            // ✅ parseClaimsJws 會做：驗簽、格式、exp 檢查（過期會丟 ExpiredJwtException）
            return parser.parseClaimsJws(token).getBody();

        } catch (ExpiredJwtException e) {
            throw new Exception("Token expired");
        } catch (UnsupportedJwtException | MalformedJwtException | SecurityException | IllegalArgumentException e) {
            throw  new Exception("Invalid token");
        }
    }


    /**
     * 用 Refresh Token 產生新的 Access Token：
     * - 驗簽、issuer、exp
     * - 檢查 typ=refresh
     * - 取 subject(username)
     * - 發新的 access token
     */
    public static String refreshAccessToken(String refreshToken) throws Exception {
        Claims claims = parseAndValidate(refreshToken);

        String typ = claims.get("typ", String.class);
        if (!"refresh".equals(typ)) {
            throw new Exception("Not a refresh token");
        }

        String username = claims.getSubject();
        if (username == null || username.isBlank()) {
            throw new Exception("Refresh token missing subject");
        }

        return generateAccessTokenByUsername(username);
    }




}
