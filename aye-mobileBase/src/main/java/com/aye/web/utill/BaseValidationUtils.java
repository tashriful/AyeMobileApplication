package com.aye.web.utill;

import com.aye.web.exception.GeneralExceptions;
import com.aye.web.model.common.BankLineM;
import com.aye.web.model.common.InventoryInformationsM;
import com.aye.web.model.common.OrgHierarchyM;
import com.aye.web.repo.*;
import com.aye.web.service.MasterItemMService;
import com.aye.web.service.OrdItemDetailMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BaseValidationUtils {

    private final MasterItemMService masterItemMService;
    private final CustomerLineMRepo customerLineMRepo;
    private final BankLineMRepo bankLineMRepo;
    private final OrgHierarchyMRepo orgHierarchyMRepo;
    private final InventoryInformationsMRepo inventoryInformationsMRepo;

    private final OrdItemDetailMService ordItemDetailMService;

    @Autowired
    public BaseValidationUtils(MasterItemMService masterItemMService, CustomerLineMRepo customerLineMRepo, BankLineMRepo bankLineMRepo, OrgHierarchyMRepo orgHierarchyMRepo, InventoryInformationsMRepo inventoryInformationsMRepo, OrdItemDetailMService ordItemDetailMService) {
        this.masterItemMService = masterItemMService;
        this.customerLineMRepo = customerLineMRepo;
        this.bankLineMRepo = bankLineMRepo;
        this.orgHierarchyMRepo = orgHierarchyMRepo;
        this.inventoryInformationsMRepo = inventoryInformationsMRepo;
        this.ordItemDetailMService = ordItemDetailMService;
    }



    public void validateCustomerId(String customerId) throws GeneralExceptions {
        if (customerId == null || customerLineMRepo.findById(customerId).isEmpty()) {
            throw new GeneralExceptions("Invalid Customer or Customer Id Missing!", null);
        }
    }



    public void validateItemCode(String id) throws GeneralExceptions {
        if (id == null || id.isEmpty()) {
            throw new GeneralExceptions("Item Not Found!", null);
        }
        if (masterItemMService.findById(id) == null) {
            throw new GeneralExceptions("Invalid Item!", null);
        }
    }

    public void validateItemDetail(String id) throws GeneralExceptions {
        if (id == null || id.isEmpty()) {
            throw new GeneralExceptions("Item Not Found!", null);
        }
        if (ordItemDetailMService.findById(id) == null) {
            throw new GeneralExceptions("Invalid Item!", null);
        }
    }

    public void validateBankId(String id) throws GeneralExceptions {
        if (id == null || id.isEmpty()) {
            throw new GeneralExceptions("Bank Line Empty!!", null);
        }
        Optional<BankLineM> bankLineM = bankLineMRepo.findById(id);
        if (bankLineM.isEmpty()) {
            throw new GeneralExceptions("Invalid Bank!", null);
        }
    }


    public void validateOrgHierarchy(String id) throws GeneralExceptions {
        if (id == null || id.isEmpty()) {
            throw new GeneralExceptions("Org Empty!!", null);
        }
        Optional<OrgHierarchyM> orgHierarchyMRepoById = orgHierarchyMRepo.findById(id);
        if (orgHierarchyMRepoById.isEmpty()) {
            throw new GeneralExceptions("Invalid Org!", null);
        }
    }

    public void validateInvOrg(String id) throws GeneralExceptions {
        if (id == null || id.isEmpty()) {
            throw new GeneralExceptions("Inv Org Empty!!", null);
        }
        Optional<InventoryInformationsM> informationsM = inventoryInformationsMRepo.findById(id);
        if (informationsM.isEmpty()) {
            throw new GeneralExceptions("Invalid Org!", null);
        }
    }

    private boolean isZeroOrNullOrNegative(BigDecimal quantity) {
        return quantity == null || quantity.compareTo(BigDecimal.ZERO) <= 0;
    }

    private boolean isGreaterThanOrderLineQty(BigDecimal schdRelatedQty, BigDecimal ordLineQty) {
        return schdRelatedQty == null || ordLineQty == null || schdRelatedQty.compareTo(ordLineQty) > 0;
    }


}
