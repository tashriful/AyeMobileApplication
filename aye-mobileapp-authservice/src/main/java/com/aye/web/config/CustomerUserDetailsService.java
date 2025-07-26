package com.aye.web.config;

import com.aye.web.model.user.UserM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String url = "http://USER-SERVICE/msales/api/user/getUserByUserName/" + username;
        UserM userM = restTemplate.getForObject(url, UserM.class);
        if (userM != null) {
            return new CustomUserDetails(userM);
        }
        else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
}
