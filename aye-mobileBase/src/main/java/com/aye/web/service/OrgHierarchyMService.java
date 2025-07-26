package com.aye.web.service;

import com.aye.web.model.common.OrgHierarchyM;

import java.util.List;

public interface OrgHierarchyMService {
    OrgHierarchyM saveOrgHierarchyM(OrgHierarchyM orgHierarchyM);
    List<OrgHierarchyM> findAllOrgHierarchyM();
    OrgHierarchyM findOrgHierarchyMById(String id);
    OrgHierarchyM updateOrgHierarchyM(OrgHierarchyM orgHierarchyM);

}
