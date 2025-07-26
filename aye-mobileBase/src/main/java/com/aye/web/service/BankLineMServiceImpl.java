package com.aye.web.service;

import com.aye.web.model.common.BankLineM;
import com.aye.web.model.common.OrgHierarchyM;
import com.aye.web.repo.BankLineMRepo;
import com.aye.web.repo.OrgHierarchyMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankLineMServiceImpl implements BankLineMService {

    private final BankLineMRepo bankLineMRepo;
    private final OrgHierarchyMRepo orgHierarchyMRepo;

    @Autowired
    public BankLineMServiceImpl(BankLineMRepo bankLineMRepo,
                                OrgHierarchyMRepo orgHierarchyMRepo) {
        this.bankLineMRepo = bankLineMRepo;
        this.orgHierarchyMRepo = orgHierarchyMRepo;
    }

    @Override
    public List<BankLineM> findAllBankByOrg(String orgId) {
        OrgHierarchyM orgHierarchyM = orgHierarchyMRepo.findById(orgId).orElse(null);
        return bankLineMRepo.findAllByOrgHierarchyM(orgHierarchyM);
    }

    @Override
    public BankLineM findBankLineByOrgAndId(String orgId, String id) {
        OrgHierarchyM orgHierarchyM = orgHierarchyMRepo.findById(orgId).orElse(null);
        return bankLineMRepo.findBankLineMByOrgHierarchyMAndId(orgHierarchyM, id);
    }

    @Override
    public BankLineM findBankById(String id) {
        return bankLineMRepo.findById(id).orElse(null);
    }
}
