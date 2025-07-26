package com.aye.web.service;

import com.aye.web.mobileBase.exception.GeneralExceptions;
import com.aye.web.mobileBase.model.schedule.OrdDelvScheduleHeaderM;

import java.util.List;

public interface OrdDelvSchdHeaderMService {

    OrdDelvScheduleHeaderM saveDlvSchdHeader(OrdDelvScheduleHeaderM ordDelvScheduleHeaderM) throws GeneralExceptions, GeneralExceptions;
    List<OrdDelvScheduleHeaderM> getAllDelvSchdHeader();
    OrdDelvScheduleHeaderM getDelvSchdHeaderById(String id);
    OrdDelvScheduleHeaderM updateDlvSchdHeader(OrdDelvScheduleHeaderM ordDelvScheduleHeaderM);
}
