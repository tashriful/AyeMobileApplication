package com.aye.web.service;

import com.aye.web.model.order.OrdItemDetailM;

import java.util.List;

public interface OrdItemDetailMService {

    OrdItemDetailM findById(String id);

    OrdItemDetailM findByItemIdAndInvOrgId(int itemId, int invId);

    List<OrdItemDetailM> findByParentOrgAndInvOrg(int parentOrg, int invOrg);
}
