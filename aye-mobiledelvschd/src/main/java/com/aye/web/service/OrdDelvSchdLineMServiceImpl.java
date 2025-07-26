package com.aye.web.service;

import com.aye.web.mobileBase.exception.GeneralExceptions;
import com.aye.web.mobileBase.model.schedule.OrdDelvScheduleHeaderM;
import com.aye.web.mobileBase.model.schedule.OrdDelvScheduleLineM;
import com.aye.web.mobileBase.repo.OrdDelvSchdHeaderMRepo;
import com.aye.web.mobileBase.repo.OrdDelvScheduleLineMRepo;
import com.aye.web.mobileBase.utill.BaseValidationUtils;
import com.aye.web.utill.DlvValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrdDelvSchdLineMServiceImpl implements OrdDelvSchdLineMService {

    private final OrdDelvScheduleLineMRepo ordDelvScheduleLineMRepo;
    private final OrdDelvSchdHeaderMRepo ordDelvSchdHeaderMRepo;
    private final DlvValidationUtils dlvValidationUtils;

    private final BaseValidationUtils validationUtils;

    @Autowired
    public OrdDelvSchdLineMServiceImpl(OrdDelvScheduleLineMRepo ordDelvScheduleLineMRepo, OrdDelvSchdHeaderMRepo ordDelvSchdHeaderMRepo, DlvValidationUtils dlvValidationUtils, BaseValidationUtils validationUtils) {
        this.ordDelvScheduleLineMRepo = ordDelvScheduleLineMRepo;
        this.ordDelvSchdHeaderMRepo = ordDelvSchdHeaderMRepo;
        this.dlvValidationUtils = dlvValidationUtils;
        this.validationUtils = validationUtils;
    }

    @Override
    public OrdDelvScheduleLineM saveDlvSchdLine(OrdDelvScheduleLineM ordDelvScheduleLineM) throws GeneralExceptions {
        checkBeforeSaveLine(ordDelvScheduleLineM);
        OrdDelvScheduleLineM scheduleLineM = ordDelvScheduleLineMRepo.save(ordDelvScheduleLineM);
        Optional<OrdDelvScheduleLineM> savedScheduleLineM = ordDelvScheduleLineMRepo.findById(scheduleLineM.getId());
        updateSchdLineOfScdHeader(savedScheduleLineM.get());
        return scheduleLineM;
    }

    private void checkBeforeSaveLine(OrdDelvScheduleLineM ordDelvScheduleLineM) throws GeneralExceptions {
        dlvValidationUtils.validateDlvSchdHeader(ordDelvScheduleLineM.getOrdDelvScheduleHeader().getId());
        validationUtils.validateCustomerId(Objects.requireNonNull(ordDelvScheduleLineM.getCustomerLine()).getId());
        dlvValidationUtils.validateOrderLineWithHeader(ordDelvScheduleLineM.getOrderLine().getLineId(),
                ordDelvScheduleLineM.getOrderHeader().getId());
        dlvValidationUtils.validateSchdQuantity(ordDelvScheduleLineM.getScheduleQty(), ordDelvScheduleLineM.getApproveQty(),
                ordDelvScheduleLineM.getApproveDefaultUomQty(), ordDelvScheduleLineM.getOrderLine());
        validationUtils.validateItemCode(ordDelvScheduleLineM.getMasterItem().getId());

    }

    private void updateSchdLineOfScdHeader(OrdDelvScheduleLineM ordDelvScheduleLineM) {
        Optional<OrdDelvScheduleHeaderM> ordDelvScheduleHeaderM = ordDelvSchdHeaderMRepo.findById(ordDelvScheduleLineM.getOrdDelvScheduleHeader().getId());
        List<OrdDelvScheduleLineM> ordDelvScheduleLineMList = new ArrayList<>();
        if (ordDelvScheduleHeaderM.get().getOrdDelvScheduleLines() != null) {
            ordDelvScheduleLineMList = ordDelvScheduleHeaderM.get().getOrdDelvScheduleLines();
        }
        ordDelvScheduleLineMList.add(ordDelvScheduleLineM);
        ordDelvScheduleHeaderM.get().setOrdDelvScheduleLines(ordDelvScheduleLineMList);
        ordDelvSchdHeaderMRepo.save(ordDelvScheduleHeaderM.get());
    }

    @Override
    public List<OrdDelvScheduleLineM> getAllDelvSchdLine() {
        return ordDelvScheduleLineMRepo.findAll();
    }

    @Override
    public OrdDelvScheduleLineM getDelvSchdLineById(String id) {
        return ordDelvScheduleLineMRepo.findById(id).orElse(null);
    }

    @Override
    public OrdDelvScheduleLineM updateDlvSchdLine(OrdDelvScheduleLineM ordDelvScheduleLineM) {
        return ordDelvScheduleLineMRepo.save(ordDelvScheduleLineM);
    }
}
