package com.aye.web.service;



import com.aye.web.exception.GeneralExceptions;
import com.aye.web.model.ar.ArDistributionM;

import java.util.List;

public interface ArDistributionMService {
    ArDistributionM saveArDistribution(ArDistributionM arDistributionM) throws GeneralExceptions;

    List<ArDistributionM> getAllDistribution();

    ArDistributionM findDistributionById(String id);

    ArDistributionM updateDistribution(ArDistributionM arDistributionM);
}
