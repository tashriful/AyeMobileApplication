package com.aye.web.service;

import com.aye.web.mobileBase.exception.GeneralExceptions;
import com.aye.web.mobileBase.model.common.OrgHierarchyM;
import com.aye.web.mobileBase.model.order.OrderHeaderM;
import com.aye.web.mobileBase.model.schedule.OrdDelvScheduleHeaderM;
import com.aye.web.mobileBase.repo.OrdDelvSchdHeaderMRepo;
import com.aye.web.mobileBase.utill.BaseValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrdDelvSchdHeaderMServiceImpl implements OrdDelvSchdHeaderMService {

    private final OrdDelvSchdHeaderMRepo ordDelvSchdHeaderMRepo;
    private final BaseValidationUtils validationUtils;



    @Autowired
    public OrdDelvSchdHeaderMServiceImpl(OrdDelvSchdHeaderMRepo ordDelvSchdHeaderMRepo, BaseValidationUtils validationUtils) {
        this.ordDelvSchdHeaderMRepo = ordDelvSchdHeaderMRepo;
        this.validationUtils = validationUtils;
    }


    @Override
    public OrdDelvScheduleHeaderM saveDlvSchdHeader(OrdDelvScheduleHeaderM ordDelvScheduleHeaderM) throws GeneralExceptions {
        checkBeforeSave(ordDelvScheduleHeaderM);
        if (Objects.equals(ordDelvScheduleHeaderM.getScheduleNumber(), "") || ordDelvScheduleHeaderM.getScheduleNumber() == null) {
            ordDelvScheduleHeaderM.setScheduleNumber(getDelvSchdNumber(ordDelvScheduleHeaderM.getOrgHierarchy()));
        }
        return ordDelvSchdHeaderMRepo.save(ordDelvScheduleHeaderM);
    }

    private String getDelvSchdNumber(OrgHierarchyM orgHierarchy) {
        OrdDelvScheduleHeaderM prevSchdNumber = ordDelvSchdHeaderMRepo.findTopByOrgHierarchyOrderByScheduleNumberDesc(orgHierarchy);
        if (prevSchdNumber != null) {
            int schdNumber = Integer.parseInt(prevSchdNumber.getScheduleNumber());
            return String.format("%09d", schdNumber+1);
        }
        else {
            return String.format("%09d", 1);
        }
    }

    private void checkBeforeSave(OrdDelvScheduleHeaderM ordDelvScheduleHeaderM) throws GeneralExceptions {
        validationUtils.validateOrgHierarchy(ordDelvScheduleHeaderM.getOrgHierarchy().getId());
        validationUtils.validateInvOrg(ordDelvScheduleHeaderM.getInventoryInformations().getId());
    }

    @Override
    public List<OrdDelvScheduleHeaderM> getAllDelvSchdHeader() {
        return ordDelvSchdHeaderMRepo.findAll();
    }

    @Override
    public OrdDelvScheduleHeaderM getDelvSchdHeaderById(String id) {
        return ordDelvSchdHeaderMRepo.findById(id).orElse(null);
    }

    @Override
    public OrdDelvScheduleHeaderM updateDlvSchdHeader(OrdDelvScheduleHeaderM ordDelvScheduleHeaderM) {
        return ordDelvSchdHeaderMRepo.save(ordDelvScheduleHeaderM);
    }
}
