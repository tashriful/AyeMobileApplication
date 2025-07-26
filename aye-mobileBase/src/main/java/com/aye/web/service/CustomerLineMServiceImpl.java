package com.aye.web.service;

import com.aye.web.model.common.CustomerLineM;
import com.aye.web.model.common.OrgHierarchyM;
import com.aye.web.repo.CustomerLineMRepo;
import com.aye.web.repo.OrgHierarchyMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerLineMServiceImpl implements CustomerLineMService {

    private final CustomerLineMRepo customerLineMRepo;
    private final OrgHierarchyMRepo orgHierarchyMRepo;

    @Autowired
    public CustomerLineMServiceImpl(CustomerLineMRepo customerLineMRepo,
                                    OrgHierarchyMRepo orgHierarchyMRepo) {
        this.customerLineMRepo = customerLineMRepo;
        this.orgHierarchyMRepo = orgHierarchyMRepo;
    }

    @Override
    public List<CustomerLineM> findCustomerLineByOrg(String orgId) {
        Optional<OrgHierarchyM> orgHierarchyM = orgHierarchyMRepo.findById(orgId);
        if (orgHierarchyM.isPresent()) {
            return customerLineMRepo.findAllByOrgHierarchy(orgHierarchyM.get());
        }
        else return null;
    }

    @Override
    public CustomerLineM findCustomerLineByOrgAndId(String orgId, String id) {
        Optional<OrgHierarchyM> orgHierarchyM = orgHierarchyMRepo.findById(orgId);
        if (orgHierarchyM.isPresent()) {
            return customerLineMRepo.findByOrgHierarchyAndId(orgHierarchyM.get(), id);
        }
        else return null;

    }

    @Override
    public CustomerLineM findCustomerLineById(String id) {
        return customerLineMRepo.findById(id).orElse(null);
    }

    @Override
    public List<CustomerLineM> findByCustomerNameLikeIgnoreCaseAndId(String cName, String id) {
        List<CustomerLineM> customerLineMS = null;
        if(customerLineMRepo.findByNameLikeIgnoreCaseAndId(cName,id) != null) {
           customerLineMS = customerLineMRepo.findByNameLikeIgnoreCaseAndId(cName,id);
        }
        return customerLineMS;
    }

    @Override
    public List<CustomerLineM> findByOrgHierarchyAndName(OrgHierarchyM orgHierarchyM, String cName) {
        List<CustomerLineM> customerLineMS = null;
        if(customerLineMRepo.findByOrgHierarchyAndNameLikeIgnoreCase(orgHierarchyM, cName) != null) {
            customerLineMS = customerLineMRepo.findByOrgHierarchyAndNameLikeIgnoreCase(orgHierarchyM, cName);
        }
        return customerLineMS;
    }

    @Override
    public List<CustomerLineM> findByCustomerNameStartingWith(String cName) {
        return customerLineMRepo.findByNameStartingWith(cName);
    }

    @Override
    public List<CustomerLineM> findBynameRegexIgnoreCase(String cName) {
        return customerLineMRepo.findBynameRegexIgnoreCase(cName);
    }
}
