package com.aye.web.service;

import com.aye.web.model.OrgHierarchyMDto;

import java.util.List;

public interface OrgHierarchyMService {
    List<OrgHierarchyMDto> getOrgs(String accessToken);
    OrgHierarchyMDto saveOrg(OrgHierarchyMDto orgHierarchyMDto, String accessToken);
    OrgHierarchyMDto getOrgById(String id, String accessToken);

}
