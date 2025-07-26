package com.aye.web.service;


import com.aye.web.model.common.InventoryInformationsM;
import com.aye.web.model.common.OrgHierarchyM;
import com.aye.web.model.order.OrderTrnsTypeM;
import com.aye.web.repo.InventoryInformationsMRepo;
import com.aye.web.repo.OrderTrnsTypeMRepo;
import com.aye.web.repo.OrgHierarchyMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderTrnsTypeMServiceImpl implements OrderTrnsTypeMService {
    private final OrderTrnsTypeMRepo orderTrnsTypeMRepo;
    private final OrgHierarchyMRepo orgHierarchyMRepo;
    private final InventoryInformationsMRepo inventoryInformationsMRepo;

    @Autowired
    public OrderTrnsTypeMServiceImpl(OrderTrnsTypeMRepo orderTrnsTypeMRepo,
                                     OrgHierarchyMRepo orgHierarchyMRepo,
                                     InventoryInformationsMRepo inventoryInformationsMRepo) {
        this.orderTrnsTypeMRepo = orderTrnsTypeMRepo;
        this.orgHierarchyMRepo = orgHierarchyMRepo;
        this.inventoryInformationsMRepo = inventoryInformationsMRepo;
    }

    @Override
    public List<OrderTrnsTypeM> findAllOrderTrnsTypeByOrgAndInvOrg(String orgId, String invOrgId) {
        OrgHierarchyM orgHierarchyM = orgHierarchyMRepo.findById(orgId).orElse(null);
        InventoryInformationsM inventoryInformationsM = inventoryInformationsMRepo.findById(invOrgId).orElse(null);
        return orderTrnsTypeMRepo.findAllByOrgHierarchyAndInvOrg(orgHierarchyM, inventoryInformationsM);
    }

    @Override
    public OrderTrnsTypeM findById(String id) {
        return orderTrnsTypeMRepo.findById(id).orElse(null);
    }
}
