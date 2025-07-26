package com.aye.web.service;


import com.aye.web.exception.GeneralExceptions;
import com.aye.web.utill.OrdValidationUtils;
import com.aye.web.model.ar.ArDistributionM;
import com.aye.web.repo.ArDistributionMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArDistributionMServiceImpl implements ArDistributionMService {

    private final ArDistributionMRepo arDistributionRepo;

    private final OrdValidationUtils validationUtils;

    @Autowired
    public ArDistributionMServiceImpl(ArDistributionMRepo arDistributionRepo, OrdValidationUtils validationUtils) {
        this.arDistributionRepo = arDistributionRepo;
        this.validationUtils = validationUtils;
    }

    @Override
    public ArDistributionM saveArDistribution(ArDistributionM arDistributionM) throws GeneralExceptions {
        checkBeforeSave(arDistributionM);
        return arDistributionRepo.save(arDistributionM);
    }

    private void checkBeforeSave(ArDistributionM arDistributionM) throws GeneralExceptions {
        validationUtils.validateOrderHeader(arDistributionM.getOrderHeaderM().getId());
        validationUtils.validatePayment(arDistributionM.getReceipt().getId());
    }

    @Override
    public List<ArDistributionM> getAllDistribution() {
        return arDistributionRepo.findAll();
    }

    @Override
    public ArDistributionM findDistributionById(String id) {
        return arDistributionRepo.findById(id).orElse(null);
    }

    @Override
    public ArDistributionM updateDistribution(ArDistributionM arDistributionM) {
        return arDistributionRepo.save(arDistributionM);
    }
}
