package com.aye.web.service;

import com.aye.web.model.AccessTemplateDetailsMDto;
import com.aye.web.model.OrgHierarchyMDto;
import com.aye.web.model.UserMenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AccessTemplateDetailsServiceImpl implements AccessTemplateDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${msales.baseurl}")
    private String msalesBaseUrl;

    @Autowired
    private OrgHierarchyMService orgHierarchyMService;

    @Autowired
    private UserMenuService userMenuService;

    @Override
    public List<AccessTemplateDetailsMDto> getAccessTemplatesDetails(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/getAllAccessTemplateDetails";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer "+accessToken);
        HttpEntity<AccessTemplateDetailsMDto> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<AccessTemplateDetailsMDto>> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, new ParameterizedTypeReference<List<AccessTemplateDetailsMDto>>(){});
        return response.getBody();
    }

    @Override
    public List<AccessTemplateDetailsMDto> getAccessTemplatesDetailsByTemplate(String templateId, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/getTmpltDetailsByTmplt/accessTemplate/"+templateId;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer "+accessToken);
        HttpEntity<AccessTemplateDetailsMDto> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<AccessTemplateDetailsMDto>> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, new ParameterizedTypeReference<List<AccessTemplateDetailsMDto>>(){});
        return response.getBody();
    }

    @Override
    public AccessTemplateDetailsMDto saveAccessTemplateDetails(AccessTemplateDetailsMDto accessTemplateDetailsMDto, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

//        OrgHierarchyMDto orgById = orgHierarchyMService.getOrgById(accessTemplateDetailsMDto.getOrgHierarchyId(), accessToken);
//        UserMenuDto userMenuById = userMenuService.findUserMenuById(accessTemplateDetailsMDto.getUserMenuId(), accessToken);

//        accessTemplateDetailsMDto.setOrgHierarchyM(orgById);
//        accessTemplateDetailsMDto.setUserMenuM(userMenuById);

        String url = msalesBaseUrl+"/user/saveAccessTemplateDetails";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<AccessTemplateDetailsMDto> requestHttpEntity = new HttpEntity<>(accessTemplateDetailsMDto, httpHeaders);

        ResponseEntity<AccessTemplateDetailsMDto> response = restTemplate.exchange(url, HttpMethod.POST, requestHttpEntity, AccessTemplateDetailsMDto.class);
        return response.getBody();
    }

    @Override
    public AccessTemplateDetailsMDto getAccessTemplateDetailsById(String id, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/user/getAccessTemplateDetailsById/"+id;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<AccessTemplateDetailsMDto> requestHttpEntity = new HttpEntity<>( httpHeaders);

        ResponseEntity<AccessTemplateDetailsMDto> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, AccessTemplateDetailsMDto.class);
        return response.getBody();
    }
}
