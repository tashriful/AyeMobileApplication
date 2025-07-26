package com.aye.web.service;

import com.aye.web.model.AccessTemplateDto;
import com.aye.web.model.OrgHierarchyMDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrgHierarchyMServiceImpl implements OrgHierarchyMService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${msales.baseurl}")
    private String msalesBaseUrl;


    @Override
    public List<OrgHierarchyMDto> getOrgs(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/ord/getAllOrgHierarchy";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<AccessTemplateDto> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<OrgHierarchyMDto>> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, new ParameterizedTypeReference<List<OrgHierarchyMDto>>() {
        });
        return response.getBody();
    }

    @Override
    public OrgHierarchyMDto saveOrg(OrgHierarchyMDto orgHierarchyMDto, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/ord/saveOrgHierarchy";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<OrgHierarchyMDto> requestHttpEntity = new HttpEntity<>(orgHierarchyMDto, httpHeaders);

        ResponseEntity<OrgHierarchyMDto> response = restTemplate.exchange(url, HttpMethod.POST, requestHttpEntity, OrgHierarchyMDto.class);
        return response.getBody();
    }

    @Override
    public OrgHierarchyMDto getOrgById(String id, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/ord/getOrgHierarchyById/"+id;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<OrgHierarchyMDto> requestHttpEntity = new HttpEntity<>( httpHeaders);

        ResponseEntity<OrgHierarchyMDto> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, OrgHierarchyMDto.class);
        return response.getBody();
    }
}
