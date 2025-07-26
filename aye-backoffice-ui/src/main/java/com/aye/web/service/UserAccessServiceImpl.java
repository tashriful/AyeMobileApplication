package com.aye.web.service;

import com.aye.web.model.UserAccessMDto;
import com.aye.web.model.UserMenuDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class UserAccessServiceImpl implements UserAccessService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${msales.baseurl}")
    private String msalesBaseUrl;

    @Override
    public List<UserAccessMDto> getUserAccessByUser(String accessToken, String userId) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/getUserAccess/user/"+userId;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<UserAccessMDto> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<UserAccessMDto>> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, new ParameterizedTypeReference<List<UserAccessMDto>>(){});
        return response.getBody();
    }

    @Override
    public UserAccessMDto saveUserAccess(UserAccessMDto userAccessMDto, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/saveUserAccess";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<UserAccessMDto> requestHttpEntity = new HttpEntity<>(userAccessMDto, httpHeaders);

        ResponseEntity<UserAccessMDto> response = restTemplate.exchange(url, HttpMethod.POST, requestHttpEntity, UserAccessMDto.class);
        return response.getBody();
    }

    @Override
    public UserAccessMDto findUserAccessById(String id, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/getUserAccessById/"+id;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<UserAccessMDto> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<UserAccessMDto> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, UserAccessMDto.class);
        return response.getBody();
    }
}
