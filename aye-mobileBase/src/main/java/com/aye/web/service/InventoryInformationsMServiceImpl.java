package com.aye.web.service;

import com.aye.web.model.common.InventoryInformationsM;
import com.aye.web.model.common.OrgHierarchyM;
import com.aye.web.repo.InventoryInformationsMRepo;
import com.aye.web.repo.OrgHierarchyMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryInformationsMServiceImpl implements InventoryInformationsMService {

    private InventoryInformationsMRepo inventoryInformationsMRepo;
    private final OrgHierarchyMRepo orgHierarchyMRepo;

    @Autowired
    public InventoryInformationsMServiceImpl(InventoryInformationsMRepo inventoryInformationsMRepo,
                                             OrgHierarchyMRepo orgHierarchyMRepo) {
        this.inventoryInformationsMRepo = inventoryInformationsMRepo;
        this.orgHierarchyMRepo = orgHierarchyMRepo;
    }

    @Override
    public InventoryInformationsM saveInventoryInformationsM(InventoryInformationsM inventoryInformationsM) {
        return inventoryInformationsMRepo.save(inventoryInformationsM);
    }

    @Override
    public List<InventoryInformationsM> findAllInventoryInformationsM() {
        return inventoryInformationsMRepo.findAll();
    }

    @Override
    public InventoryInformationsM findInventoryInformationsMById(String id) {
        return inventoryInformationsMRepo.findById(id).orElse(null);
    }

    @Override
    public InventoryInformationsM updateInventoryInformationsM(InventoryInformationsM inventoryInformationsM) {
        return inventoryInformationsMRepo.save(inventoryInformationsM);
    }

    @Override
    public List<InventoryInformationsM> findInventoriesByOrg(String orgId) {
        Optional<OrgHierarchyM> orgHierarchyM = orgHierarchyMRepo.findById(orgId);
        return orgHierarchyM.map(hierarchyM -> inventoryInformationsMRepo.findAllByOrgHierarchy(hierarchyM)).orElse(null);
    }
}
