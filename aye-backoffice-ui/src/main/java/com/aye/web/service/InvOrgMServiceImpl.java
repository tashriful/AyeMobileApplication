package com.aye.web.service;

import com.aye.web.model.AccessTemplateDto;
import com.aye.web.model.InvOrgMDto;
import com.aye.web.model.OrgHierarchyMDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class InvOrgMServiceImpl implements InvOrgMService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${msales.baseurl}")
    private String msalesBaseUrl;

    @Autowired
    private OrgHierarchyMService orgHierarchyMService;

    @Override
    public List<InvOrgMDto> getInvOrgs(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/ord/getAllInventory";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<AccessTemplateDto> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<InvOrgMDto>> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, new ParameterizedTypeReference<List<InvOrgMDto>>() {
        });
        return response.getBody();
    }

    @Override
    public InvOrgMDto saveInvOrg(InvOrgMDto invOrgMDto, String accessToken) {

        OrgHierarchyMDto orgById = orgHierarchyMService.getOrgById(invOrgMDto.getOrgHierarchyId(), accessToken);
        invOrgMDto.setOrgHierarchy(orgById);

        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/ord/saveInventory";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<InvOrgMDto> requestHttpEntity = new HttpEntity<>(invOrgMDto, httpHeaders);

        ResponseEntity<InvOrgMDto> response = restTemplate.exchange(url, HttpMethod.POST, requestHttpEntity, InvOrgMDto.class);
        return response.getBody();
    }

    @Override
    public InvOrgMDto getInvOrgById(String id, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/ord/getInventoryById/"+id;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<InvOrgMDto> requestHttpEntity = new HttpEntity<>( httpHeaders);

        ResponseEntity<InvOrgMDto> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, InvOrgMDto.class);
        return response.getBody();
    }

    @Override
    public List<InvOrgMDto> getInvOrgsByOrg(String orgId, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/ord/getInventoriesByOrg/"+orgId;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<InvOrgMDto> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<InvOrgMDto>> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, new ParameterizedTypeReference<List<InvOrgMDto>>() {
        });
        return response.getBody();

    }
}
