package com.aye.web.service;

import com.aye.web.model.common.CustomerLineM;
import com.aye.web.model.common.OrgHierarchyM;

import java.util.List;

public interface CustomerLineMService {
    List<CustomerLineM> findCustomerLineByOrg(String orgId);

    CustomerLineM findCustomerLineByOrgAndId(String orgId, String id);
    CustomerLineM findCustomerLineById(String id);
    List<CustomerLineM> findByCustomerNameLikeIgnoreCaseAndId(String cName, String id);
    List<CustomerLineM> findByOrgHierarchyAndName(OrgHierarchyM orgHierarchyM, String cName);
    List<CustomerLineM> findByCustomerNameStartingWith(String cName);
    List<CustomerLineM> findBynameRegexIgnoreCase(String cName);
}
