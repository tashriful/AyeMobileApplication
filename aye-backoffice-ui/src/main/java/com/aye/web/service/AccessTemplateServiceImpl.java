package com.aye.web.service;

import com.aye.web.model.AccessTemplateDto;
import com.aye.web.model.TokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AccessTemplateServiceImpl implements AccessTemplateService {


    @Autowired
    private RestTemplate restTemplate;

    @Value("${msales.baseurl}")
    private String msalesBaseUrl;

    @Override
    public List<AccessTemplateDto> getAccessTemplates(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/getAllAccessTemplate";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer "+accessToken);
        HttpEntity<TokenRequest> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<AccessTemplateDto>> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, new ParameterizedTypeReference<List<AccessTemplateDto>>(){});
        return response.getBody();
    }

    @Override
    public AccessTemplateDto saveAccessTemplate(AccessTemplateDto accessTemplateDto, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/saveAccessTemplate";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<AccessTemplateDto> requestHttpEntity = new HttpEntity<>(accessTemplateDto, httpHeaders);

        ResponseEntity<AccessTemplateDto> response = restTemplate.exchange(url, HttpMethod.POST, requestHttpEntity, AccessTemplateDto.class);
        return response.getBody();
    }

    @Override
    public AccessTemplateDto getAccessTemplateById(String id, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/getAccessTemplateById/"+id;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<AccessTemplateDto> requestHttpEntity = new HttpEntity<>( httpHeaders);

        ResponseEntity<AccessTemplateDto> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, AccessTemplateDto.class);
        return response.getBody();
    }
}
