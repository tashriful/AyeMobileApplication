package com.aye.web.service;

import com.aye.web.model.TokenRequest;
import com.aye.web.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserRegServiceImpl implements UserRegService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${msales.baseurl}")
    private String msalesBaseUrl;

    @Override
    public List<UserDto> getUsers(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/getAllUser";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer "+accessToken);
        HttpEntity<TokenRequest> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<UserDto>> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, new ParameterizedTypeReference<List<UserDto>>(){});
        return response.getBody();
    }

    private TokenRequest setUpTokenRequest() {
        return new TokenRequest("hafiz", "1234");
    }

    @Override
    public UserDto saveUser(UserDto userDto, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/saveUser";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<UserDto> requestHttpEntity = new HttpEntity<>(userDto, httpHeaders);

        ResponseEntity<UserDto> response = restTemplate.exchange(url, HttpMethod.POST, requestHttpEntity, UserDto.class);
        return response.getBody();
    }

    @Override
    public UserDto getUserById(String id, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/getUserById/"+id;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer "+accessToken);
        HttpEntity<TokenRequest> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<UserDto> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, UserDto.class);
        return response.getBody();
    }
}
