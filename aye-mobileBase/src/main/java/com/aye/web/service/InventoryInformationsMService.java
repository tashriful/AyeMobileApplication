package com.aye.web.service;

import com.aye.web.model.common.InventoryInformationsM;

import java.util.List;

public interface InventoryInformationsMService {
    InventoryInformationsM saveInventoryInformationsM(InventoryInformationsM inventoryInformationsM);
    List<InventoryInformationsM> findAllInventoryInformationsM();
    InventoryInformationsM findInventoryInformationsMById(String id);
    InventoryInformationsM updateInventoryInformationsM(InventoryInformationsM inventoryInformationsM);

    List<InventoryInformationsM> findInventoriesByOrg(String orgId);
}
