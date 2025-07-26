package com.aye.web.repo;

import com.aye.web.model.common.CommonEnumM;
import com.aye.web.model.common.CustomerLineM;
import com.aye.web.model.common.OrgHierarchyM;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerLineMRepo extends MongoRepository<CustomerLineM, String> {

    List<CustomerLineM> findAllByOrgHierarchy(OrgHierarchyM orgHierarchyM);
    CustomerLineM findByOrgHierarchyAndId(OrgHierarchyM org, String id);
    List<CustomerLineM> findByOrgHierarchyAndCustomerTypes(OrgHierarchyM org, CommonEnumM.CustomerTypes customerTypes);

    List<CustomerLineM> findByNameLikeIgnoreCaseAndId(String cName, String id);
    List<CustomerLineM> findByNameLikeIgnoreCase(String cName);
    List<CustomerLineM> findByNameStartingWith(String cName);
    List<CustomerLineM> findBynameRegexIgnoreCase(String cName);
    List<CustomerLineM> findByOrgHierarchyAndNameLikeIgnoreCase(OrgHierarchyM orgHierarchyM, String cName);
}
