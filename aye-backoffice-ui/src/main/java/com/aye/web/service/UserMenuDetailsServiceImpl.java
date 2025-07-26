package com.aye.web.service;

import com.aye.web.model.UserMenuDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class UserMenuDetailsServiceImpl implements UserMenuDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${msales.baseurl}")
    private String msalesBaseUrl;

    @Override
    public List<UserMenuDetailsDto> getAllUserMenuDetails(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/getAllMenuDetails";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<UserMenuDetailsDto> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<UserMenuDetailsDto>> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, new ParameterizedTypeReference<List<UserMenuDetailsDto>>(){});
        return response.getBody();
    }

    @Override
    public List<UserMenuDetailsDto> getAllUserMenuDetailsByMenu(String accessToken, String menuId) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/getMenuDetailsByUserMenu/"+menuId;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<UserMenuDetailsDto> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<UserMenuDetailsDto>> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, new ParameterizedTypeReference<List<UserMenuDetailsDto>>(){});
        return response.getBody();
    }

    @Override
    public UserMenuDetailsDto saveUserMenuDetails(UserMenuDetailsDto userMenuDetailsDto, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/saveMenuDetails";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<UserMenuDetailsDto> requestHttpEntity = new HttpEntity<>(userMenuDetailsDto, httpHeaders);

        ResponseEntity<UserMenuDetailsDto> response = restTemplate.exchange(url, HttpMethod.POST, requestHttpEntity, UserMenuDetailsDto.class);
        return response.getBody();
    }

    @Override
    public UserMenuDetailsDto findUserMenuDetailsById(String id, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/getMenuDetailsById/"+id;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<UserMenuDetailsDto> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<UserMenuDetailsDto> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, UserMenuDetailsDto.class);
        return response.getBody();
    }
}
