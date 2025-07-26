package com.aye.web.repo;

import com.aye.web.model.common.BankLineM;
import com.aye.web.model.common.OrgHierarchyM;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BankLineMRepo extends MongoRepository<BankLineM, String> {
    List<BankLineM> findAllByOrgHierarchyM(OrgHierarchyM orgHierarchyM);
    BankLineM findBankLineMByOrgHierarchyMAndId(OrgHierarchyM orgHierarchyM, String id);
}
