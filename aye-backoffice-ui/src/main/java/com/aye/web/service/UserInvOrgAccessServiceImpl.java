package com.aye.web.service;

import com.aye.web.model.UserInvOrgAccessDto;
import com.aye.web.model.UserMenuDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserInvOrgAccessServiceImpl implements UserInvOrgAccessService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${msales.baseurl}")
    private String msalesBaseUrl;

    @Override
    public List<UserInvOrgAccessDto> getAllInvOrgAccessByTmpltDtl(String tmpltDtlId, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/getAllUserInvOrgAccessByTempDtl/"+tmpltDtlId;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<UserInvOrgAccessDto> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<UserInvOrgAccessDto>> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, new ParameterizedTypeReference<List<UserInvOrgAccessDto>>(){});
        return response.getBody();
    }

    @Override
    public UserInvOrgAccessDto saveUserInvOrgAccess(UserInvOrgAccessDto userInvOrgAccessDto, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/saveUserInvOrgAccess";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<UserInvOrgAccessDto> requestHttpEntity = new HttpEntity<>(userInvOrgAccessDto, httpHeaders);

        ResponseEntity<UserInvOrgAccessDto> response = restTemplate.exchange(url, HttpMethod.POST, requestHttpEntity, UserInvOrgAccessDto.class);
        return response.getBody();
    }
}
