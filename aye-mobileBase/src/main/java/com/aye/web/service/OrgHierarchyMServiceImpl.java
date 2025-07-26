package com.aye.web.service;

import com.aye.web.model.common.OrgHierarchyM;
import com.aye.web.repo.OrgHierarchyMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgHierarchyMServiceImpl implements OrgHierarchyMService {

    private OrgHierarchyMRepo orgHierarchyMRepo;

    @Autowired
    public OrgHierarchyMServiceImpl(OrgHierarchyMRepo orgHierarchyMRepo) {
        this.orgHierarchyMRepo = orgHierarchyMRepo;
    }

    @Override
    public OrgHierarchyM saveOrgHierarchyM(OrgHierarchyM orgHierarchyM) {
        return orgHierarchyMRepo.save(orgHierarchyM);
    }

    @Override
    public List<OrgHierarchyM> findAllOrgHierarchyM() {
        return orgHierarchyMRepo.findAll();
    }

    @Override
    public OrgHierarchyM findOrgHierarchyMById(String id) {
        return orgHierarchyMRepo.findById(id).orElse(null);
    }

    @Override
    public OrgHierarchyM updateOrgHierarchyM(OrgHierarchyM orgHierarchyM) {
        return orgHierarchyMRepo.save(orgHierarchyM);
    }
}
