package com.aye.web.service;

import com.aye.web.model.common.MasterItemM;

import java.util.List;

public interface MasterItemMService {
    List<MasterItemM> findByInvOrg(String invOrgId);
    List<MasterItemM> findByInvOrgAndItemCode(String invOrg, String itemCode);
    MasterItemM findByInvOrgAndId(String invOrgId, String id);
    MasterItemM findById(String id);

}
