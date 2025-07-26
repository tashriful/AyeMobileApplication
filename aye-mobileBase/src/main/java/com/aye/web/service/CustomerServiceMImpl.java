package com.aye.web.service;

import com.aye.web.model.common.CommonEnumM;
import com.aye.web.model.common.CustomerLineM;
import com.aye.web.model.common.OrgHierarchyM;
import com.aye.web.repo.CustomerLineMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceMImpl implements CustomerServiceM {
    @Autowired
    private CustomerLineMRepo customerLineMRepo;

    @Override
    public List<CustomerLineM> findByOrgHierarchy(Optional<OrgHierarchyM> org) {
        return customerLineMRepo.findAllByOrgHierarchy(org.get());
    }

    @Override
    public CustomerLineM findCustomerByOrgHierarchyAndId(OrgHierarchyM org, String id) {
        return customerLineMRepo.findByOrgHierarchyAndId(org, id);
    }

    @Override
    public CustomerLineM findById(String id) {
        return customerLineMRepo.findById(id).orElse(null);
    }

    @Override
    public List<CustomerLineM> findByOrgHierarchyAndCustomerTypes(OrgHierarchyM org, CommonEnumM.CustomerTypes types) {
        return customerLineMRepo.findByOrgHierarchyAndCustomerTypes(org, types);
    }
}
