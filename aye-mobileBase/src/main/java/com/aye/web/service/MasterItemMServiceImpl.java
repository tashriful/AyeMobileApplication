package com.aye.web.service;

import com.aye.web.model.common.InventoryInformationsM;
import com.aye.web.model.common.MasterItemM;
import com.aye.web.repo.InventoryInformationsMRepo;
import com.aye.web.repo.MasterItemMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterItemMServiceImpl implements MasterItemMService {

    private final MasterItemMRepo masterItemMRepo;
    private final InventoryInformationsMRepo inventoryInformationsMRepo;

    @Autowired
    public MasterItemMServiceImpl(MasterItemMRepo masterItemMRepo,
                                  InventoryInformationsMRepo inventoryInformationsMRepo) {
        this.masterItemMRepo = masterItemMRepo;
        this.inventoryInformationsMRepo = inventoryInformationsMRepo;
    }

    @Override
    public List<MasterItemM> findByInvOrg(String invOrgId) {
        InventoryInformationsM inventoryInformationsM = inventoryInformationsMRepo.findById(invOrgId).orElse(null);
        return masterItemMRepo.findAllByInventoryInformationsM(inventoryInformationsM);
    }

    @Override
    public List<MasterItemM> findByInvOrgAndItemCode(String invOrg, String itemCode) {
        InventoryInformationsM inventoryInformationsM = inventoryInformationsMRepo.findById(invOrg).orElse(null);
        return masterItemMRepo.findAllByInventoryInformationsMAndItemCodeLikeIgnoreCase(inventoryInformationsM, itemCode);
    }

    @Override
    public MasterItemM findByInvOrgAndId(String invOrgId, String id) {
        InventoryInformationsM inventoryInformationsM = inventoryInformationsMRepo.findById(invOrgId).orElse(null);
        return masterItemMRepo.findByInventoryInformationsMAndId(inventoryInformationsM, id);
    }

    @Override
    public MasterItemM findById(String id) {
        return masterItemMRepo.findById(id).orElse(null);
    }
}
