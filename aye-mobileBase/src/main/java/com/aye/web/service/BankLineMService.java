package com.aye.web.service;

import com.aye.web.model.common.BankLineM;

import java.util.List;

public interface BankLineMService {
    List<BankLineM> findAllBankByOrg(String orgId);

    BankLineM findBankLineByOrgAndId(String orgId, String id);
    BankLineM findBankById(String id);
}
