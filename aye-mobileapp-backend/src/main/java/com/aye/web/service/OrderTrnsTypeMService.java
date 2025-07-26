package com.aye.web.service;

import com.aye.web.model.order.OrderTrnsTypeM;

import java.util.List;

public interface OrderTrnsTypeMService {
    List<OrderTrnsTypeM> findAllOrderTrnsTypeByOrgAndInvOrg(String orgId, String invOrgId);
    OrderTrnsTypeM findById(String id);
}
