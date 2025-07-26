package com.aye.web.service;

import com.aye.web.mobileBase.exception.GeneralExceptions;
import com.aye.web.mobileBase.model.schedule.OrdDelvScheduleLineM;

import java.util.List;

public interface OrdDelvSchdLineMService {
    OrdDelvScheduleLineM saveDlvSchdLine(OrdDelvScheduleLineM ordDelvScheduleLineM) throws GeneralExceptions;
    List<OrdDelvScheduleLineM> getAllDelvSchdLine();
    OrdDelvScheduleLineM getDelvSchdLineById(String id);
    OrdDelvScheduleLineM updateDlvSchdLine(OrdDelvScheduleLineM ordDelvScheduleHeaderM);
}
