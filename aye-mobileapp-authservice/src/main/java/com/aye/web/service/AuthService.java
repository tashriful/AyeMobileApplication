package com.aye.web.service;

import com.aye.web.config.CustomUserDetails;
import com.aye.web.model.user.UserAuth;
import com.aye.web.model.user.UserM;
import com.aye.web.model.user.UserMenuAuth;
import com.aye.web.repo.user.UserMRepo;
import com.aye.web.utill.JwtUtill;
import com.aye.web.utill.RefreshTokenUtill;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    private JwtUtill jwtUtill;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RefreshTokenUtill refreshTokenUtill;
    @Autowired
    private UserMRepo userMRepo;


    public String generateToken(String username) {
        return jwtUtill.generateToken(username);
    }

    public void validateToken(String token) {
        jwtUtill.validateToken(token);
    }

    public String generateRefreshToken(String username) {
        return refreshTokenUtill.generateRefreshToken(username);
    }

    public String validateRefreshToken(String token) {
        try {
            refreshTokenUtill.validateToken(token);
        } catch (ExpiredJwtException e) {
            // Token has expired
            throw new JwtException("Refresh Token has expired");
        } catch (MalformedJwtException e) {
            // Token is malformed or invalid
            throw new JwtException("Malformed or invalid Refresh token");
        } catch (SignatureException e) {
            // Token signature is invalid
            throw new JwtException("Invalid Refresh token signature");
        } catch (JwtException e) {
            // Other JWT-related exceptions
            throw new JwtException("JWT validation failed");
        } catch (Exception e) {
            // Catch-all for other exceptions
            throw new JwtException("Unauthorized access to the application");
        }
        String username = refreshTokenUtill.extractUsername(token);
        String url = "http://USER-SERVICE/msales/api/user/getUserByUserName/" + username;
        UserM userM = restTemplate.getForObject(url, UserM.class);

        if (userM == null) {
            throw new RuntimeException("Invalid User!");
        }
        return jwtUtill.generateToken(username);
    }

    public List<UserMenuAuth> findAllAccessMenusByUser(String username) {
        String url = "http://USER-SERVICE/msales/api/user/getUserAccessMenusByUserName/" + username;
        HttpHeaders httpHeaders = new HttpHeaders();


        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<UserMenuAuth>> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<UserMenuAuth>> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, new ParameterizedTypeReference<>() {
        });
        return response.getBody();
    }

    public UserAuth findUserByusername(String username) {
        String url = "http://USER-SERVICE/msales/api/user/getUserByUserName/" + username;
        UserM userM = restTemplate.getForObject(url, UserM.class);
        if (userM != null) {
            return UserAuth.builder().id(userM.getId())
                    .name(userM.getName())
                    .designation(userM.getDesignation())
                    .username(userM.getUsername())
                    .startDate(userM.getStartDate())
                    .endDate(userM.getEndDate())
                    .isActive(userM.isActive()).build();
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

}
