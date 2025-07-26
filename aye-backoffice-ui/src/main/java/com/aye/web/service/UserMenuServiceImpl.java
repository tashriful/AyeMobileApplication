package com.aye.web.service;

import com.aye.web.model.TokenRequest;
import com.aye.web.model.UserMenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class UserMenuServiceImpl implements UserMenuService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${msales.baseurl}")
    private String msalesBaseUrl;

    @Override
    public List<UserMenuDto> findAllUserMenu(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/getAllMenu";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer "+accessToken);
        HttpEntity<TokenRequest> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<UserMenuDto>> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, new ParameterizedTypeReference<List<UserMenuDto>>(){});
        return response.getBody();
    }

    @Override
    public UserMenuDto saveUserMenu(UserMenuDto userMenuDto, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/saveMenu";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<UserMenuDto> requestHttpEntity = new HttpEntity<>(userMenuDto, httpHeaders);

        ResponseEntity<UserMenuDto> response = restTemplate.exchange(url, HttpMethod.POST, requestHttpEntity, UserMenuDto.class);
        return response.getBody();
    }

    @Override
    public UserMenuDto findUserMenuById(String id, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/getMenuById/"+id;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<UserMenuDto> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<UserMenuDto> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, UserMenuDto.class);
        return response.getBody();
    }
}
