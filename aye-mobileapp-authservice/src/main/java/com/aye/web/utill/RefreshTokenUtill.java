package com.aye.web.utill;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class RefreshTokenUtill {

    @Value("${jwt.refreshToken.validity.seconds}") // Injected from application.yml
    private long refreshTokenValiditySeconds;

    public static final String SECRET = "b0f7cebf7b40ab14f706dccdce15037952bd44e12a82f0be2bac15c9fbd8489b";


    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    public String extractUsername(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
        String username = claimsJws.getBody().getSubject();
        // Now, 'username' contains the extracted username from the JWT
        // You can use it as needed in your validation logic or elsewhere.
        return username;
    }


    public String generateRefreshToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("type" , "Refresh Token");
        return createRefreshToken(claims, userName);
    }

    private String createRefreshToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValiditySeconds * 1000))   //converting sec to milisecond
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
