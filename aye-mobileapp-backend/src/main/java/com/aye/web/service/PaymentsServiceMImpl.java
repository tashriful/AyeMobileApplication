package com.aye.web.service;


import com.aye.web.exception.GeneralExceptions;
import com.aye.web.utill.OrdValidationUtils;
import com.aye.web.model.ar.AttachmentM;
import com.aye.web.model.ar.PaymentStatus;
import com.aye.web.model.ar.PaymentsM;
import com.aye.web.model.ar.PaymentsMSearchDto;
import com.aye.web.repo.ArDistributionMRepo;
import com.aye.web.repo.AttachmentMRepo;
import com.aye.web.repo.PaymentsMRepo;
import com.aye.web.utill.BaseValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PaymentsServiceMImpl implements PaymentsServiceM {

    private final PaymentsMRepo paymentsMRepo;

    private final CustomerLineMService customerLineMService;

    private final AttachmentService attachmentService;

    private final ArDistributionMRepo arDistributionRepo;

    private final AttachmentMRepo attachmentMRepo;

    private final BaseValidationUtils baseValidationUtils;


    private final OrdValidationUtils validationUtils;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public PaymentsServiceMImpl(PaymentsMRepo paymentsMRepo, CustomerLineMService customerLineMService, AttachmentService attachmentService, ArDistributionMRepo arDistributionRepo, AttachmentMRepo attachmentMRepo, BaseValidationUtils baseValidationUtils, OrdValidationUtils validationUtils, MongoTemplate mongoTemplate) {
        this.paymentsMRepo = paymentsMRepo;
        this.customerLineMService = customerLineMService;
        this.attachmentService = attachmentService;
        this.arDistributionRepo = arDistributionRepo;
        this.attachmentMRepo = attachmentMRepo;
        this.baseValidationUtils = baseValidationUtils;
        this.validationUtils = validationUtils;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public PaymentsM savePayment(PaymentsM paymentsM) throws GeneralExceptions, IOException {
        checkBeforePayment(paymentsM);

        ArrayList<AttachmentM> attachmentMArrayList = new ArrayList<>();
        for (MultipartFile file : paymentsM.getFile()){

            String attachmentId = attachmentService.addFile(file);
            AttachmentM attachmentM = new AttachmentM();
            attachmentM.setId(attachmentId);
            attachmentM.setFileName(file.getOriginalFilename());
            attachmentM.setContentType(file.getContentType());
            attachmentM.setSize(file.getSize());
            attachmentM.setDownloadUrl("/downloadAttachment/"+attachmentId);
            AttachmentM savedAttachment = attachmentMRepo.save(attachmentM);
            attachmentMArrayList.add(attachmentM);
        }

        paymentsM.setFile(null);
        paymentsM.setAttachmentM(attachmentMArrayList);
        if (Objects.equals(paymentsM.getNumber(), "") || paymentsM.getNumber() == null) {
            paymentsM.setNumber(getPaymentNumber());
        }

        return paymentsMRepo.save(paymentsM);
    }

    private String getPaymentNumber() {
        PaymentsM paymentsM = paymentsMRepo.findTopByOrderByNumberDesc();
        if (paymentsM != null) {
            int paymentNumber = Integer.parseInt(paymentsM.getNumber());
            return String.format("%09d", paymentNumber+1);
        }
        else {
            return String.format("%09d", 1);
        }
    }

    private void checkBeforePayment(PaymentsM paymentsM) throws GeneralExceptions {
        baseValidationUtils.validateCustomerId(paymentsM.getCustomerLineM().getId());
        baseValidationUtils.validateBankId(paymentsM.getBankLine().getId());
    }

    @Override
    public List<PaymentsM> getAllPayments() {
//        return paymentsMRepo.findAll().stream().peek(m -> {
//            AttachmentM attachmentM = null;
//            try {
//                attachmentM = attachmentService.downloadFile(m.getFileId());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            m.setAttachmentM(attachmentM);
//        }).collect(Collectors.toList());
        return null;
    }

    @Override
    public PaymentsM findPaymentsById(String id) throws IOException {
        PaymentsM paymentsM = paymentsMRepo.findById(id).orElse(null);
//        List<AttachmentM> attachmentMS = new ArrayList<>();
//        if (paymentsM.getFileId().size() != 0) {
//            for(String fileId : paymentsM.getFileId()) {
//                AttachmentM attachmentM = attachmentService.downloadFile(fileId);
//                attachmentMS.add(attachmentM);
//            }
//        }
//        paymentsM.setAttachmentM(attachmentMS);
        return paymentsM;

    }

    @Override
    public PaymentsM updatePayments(PaymentsM paymentsM) {
        return paymentsMRepo.save(paymentsM);
    }

    @Override
    public Page<PaymentsM> searchPayment(PaymentsMSearchDto paymentsMSearchDto, Pageable pageable) {

        Query query = new Query().with(pageable).with(Sort.by(Sort.Order.asc("number")));
        List<Criteria> criterias = new ArrayList<>();

        //mandatory search fields. Ig no data given it will store null and no data found in search result
        criterias.add(Criteria.where("orgHierarchyM").is(paymentsMSearchDto.getOrgHierarchyM()));


        if (paymentsMSearchDto.getId() != null && !paymentsMSearchDto.getId().isBlank()) {
            criterias.add(Criteria.where("id").is(paymentsMSearchDto.getId()));
        }
        if (paymentsMSearchDto.getPayType() != null) {
            criterias.add(Criteria.where("payType").is(paymentsMSearchDto.getPayType()));
        }
        if (paymentsMSearchDto.getNumber() != null && !paymentsMSearchDto.getNumber().isBlank()) {
            criterias.add(Criteria.where("number").is(paymentsMSearchDto.getNumber()));
        }
        if (paymentsMSearchDto.getDate() != null) {
            criterias.add(Criteria.where("date").is(paymentsMSearchDto.getDate()));
        }
        if (paymentsMSearchDto.getTrnsDate() != null) {
            criterias.add(Criteria.where("orderStatus").is(paymentsMSearchDto.getTrnsDate()));
        }
        if (paymentsMSearchDto.getCustomerLineM() != null && !paymentsMSearchDto.getCustomerLineM().isBlank()) {
            criterias.add(Criteria.where("customerLineM").is(paymentsMSearchDto.getCustomerLineM()));
        }
        if (paymentsMSearchDto.getPaymentStatus() != null) {
            criterias.add(Criteria.where("paymentStatus").is(paymentsMSearchDto.getPaymentStatus()));
        }
        if (paymentsMSearchDto.getBankLine() != null && !paymentsMSearchDto.getBankLine().isBlank()) {
            criterias.add(Criteria.where("bankLine").is(paymentsMSearchDto.getBankLine()));
        }
        if (paymentsMSearchDto.getCustomerLineM() != null && !paymentsMSearchDto.getCustomerLineM().isBlank()) {
            criterias.add(Criteria.where("customerLineM").is(paymentsMSearchDto.getCustomerLineM()));
        }

        if (!criterias.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[0])));
        }

        return PageableExecutionUtils.getPage(mongoTemplate.find(query, PaymentsM.class), pageable,
                () -> mongoTemplate.count(query.skip(0).limit(0), PaymentsM.class));

    }

    @Override
    public void postPayment(String paymentId) throws GeneralExceptions {
        Optional<PaymentsM> paymentsM = paymentsMRepo.findById(paymentId);
        if (paymentsM.isPresent()){
            if (paymentsM.get().getPaymentStatus().equals(PaymentStatus.P)){
                throw new GeneralExceptions("Payment Already Posted!", null);
            }
            paymentsM.get().setPaymentStatus(PaymentStatus.P);
            paymentsMRepo.save(paymentsM.get());
        }
    }

    @Override
    public List<PaymentsM> findPaymentByStatus(PaymentStatus paymentStatus) {
        List<PaymentsM> allByPaymentStatus = paymentsMRepo.findAllByPaymentStatus(paymentStatus);
        if (allByPaymentStatus != null) {
            return allByPaymentStatus;
        }
        else return null;
    }
}
