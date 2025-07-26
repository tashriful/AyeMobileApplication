package com.aye.web.controller;

import com.aye.web.common.CustomErrorResponse;
import com.aye.web.config.CustomerUserDetailsService;
import com.aye.web.model.user.AuthRequest;
import com.aye.web.model.user.AuthResponseDto;
import com.aye.web.model.user.RefreshTokenRequestDto;
import com.aye.web.model.user.UserM;
import com.aye.web.service.AuthService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;


    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("ok");
    }


    @PostMapping("/authenticate")
    public AuthResponseDto getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authenticate.isAuthenticated()) {
            return AuthResponseDto.builder()
                    .accessToken(authService.generateToken(authRequest.getUsername()))
                    .refreshToken(authService.generateRefreshToken(authRequest.getUsername()))
                    .userInfo(authService.findUserByusername(authRequest.getUsername()))
                    .userAccessList(authService.findAllAccessMenusByUser(authRequest.getUsername()))
                    .build();

        } else {
            throw new RuntimeException("invalid Username And Password");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "Token is valid";
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> callRefreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        //here we're generating new Access token, and we're taking input of a valid refresh token.
        String accessToken = null;
        try {
            accessToken = authService.validateRefreshToken(refreshTokenRequestDto.getRefreshToken());
        } catch (Exception e) {
            CustomErrorResponse customErrorResponse = new CustomErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED, ZonedDateTime.now());
            return new ResponseEntity<>(customErrorResponse, HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(AuthResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshTokenRequestDto.getRefreshToken())
                .build());


    }

    @GetMapping("/userinfo")
    public ResponseEntity<UserM> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            // User is not authenticated
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);

        if (userDetails == null) {
            // User not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(UserM.builder().username(userDetails.getUsername()).build());

    }
}
