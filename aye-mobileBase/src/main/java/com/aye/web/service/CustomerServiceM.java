package com.aye.web.service;

import com.aye.web.model.common.CommonEnumM;
import com.aye.web.model.common.CustomerLineM;
import com.aye.web.model.common.OrgHierarchyM;

import java.util.List;
import java.util.Optional;

public interface CustomerServiceM {

    List<CustomerLineM> findByOrgHierarchy(Optional<OrgHierarchyM> org);
    CustomerLineM findCustomerByOrgHierarchyAndId(OrgHierarchyM org,String id);
    CustomerLineM findById(String id);
    List<CustomerLineM> findByOrgHierarchyAndCustomerTypes(OrgHierarchyM org, CommonEnumM.CustomerTypes types);


}
