package com.aye.web.service;

import com.aye.web.model.InvOrgMDto;
import com.aye.web.model.OrgHierarchyMDto;

import java.util.List;

public interface InvOrgMService {
    List<InvOrgMDto> getInvOrgs(String accessToken);
    InvOrgMDto saveInvOrg(InvOrgMDto invOrgMDto, String accessToken);
    InvOrgMDto getInvOrgById(String id, String accessToken);

    List<InvOrgMDto> getInvOrgsByOrg(String orgId, String accessToken);

}
