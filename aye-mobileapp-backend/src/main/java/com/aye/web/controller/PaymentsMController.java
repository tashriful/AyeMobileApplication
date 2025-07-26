package com.aye.web.controller;


import com.aye.web.exception.GeneralExceptions;
import com.aye.web.service.AttachmentService;
import com.aye.web.service.PaymentsServiceM;
import com.aye.web.model.ar.AttachmentM;
import com.aye.web.model.ar.PaymentStatus;
import com.aye.web.model.ar.PaymentsM;
import com.aye.web.model.ar.PaymentsMSearchDto;
import com.aye.web.utill.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class PaymentsMController {

    private final PaymentsServiceM paymentsServiceM;
    private final AttachmentService attachmentService;

    @Autowired
    public PaymentsMController(PaymentsServiceM paymentsServiceM, AttachmentService attachmentService) {
        this.paymentsServiceM = paymentsServiceM;
        this.attachmentService = attachmentService;
    }


    @PostMapping("/payments")
    public ResponseEntity<?> savePayments(@ModelAttribute PaymentsM paymentsM) throws IOException {
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Payment saved successfully", paymentsServiceM.savePayment(paymentsM)));

    }

//    @GetMapping("/getAllPayments")
//    public ResponseEntity<List<PaymentsM>> getAllPayments(){
//        return ResponseEntity.ok(paymentsServiceM.getAllPayments());
//    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<?> getPaymentsById(@PathVariable("id") String id) throws IOException {
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Payment retrieved successfully", paymentsServiceM.findPaymentsById(id)));
    }

    @GetMapping("/downloadAttachment/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException {
        AttachmentM loadFile = attachmentService.downloadFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(loadFile.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getFileName() + "\"")
                .body(new ByteArrayResource(loadFile.getContent()));
    }

    @PutMapping("/payments/{id}")
    public ResponseEntity<?> updatePayments(@PathVariable("id") String id, @RequestBody PaymentsM paymentsM) {
        return ResponseEntity.ok(paymentsServiceM.updatePayments(paymentsM));
    }

    @GetMapping("/searchPayment")
    public ResponseEntity<Page<PaymentsM>> searchPayment(@RequestBody PaymentsMSearchDto paymentsMSearchDto,
                                                         @RequestParam(required = false) Integer page,
                                                         @RequestParam(required = false) Integer size) {


        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 10);

        Page<PaymentsM> paymentsMPage = paymentsServiceM.searchPayment(paymentsMSearchDto, pageable);
        return ResponseEntity.ok(paymentsMPage);
    }

    @GetMapping("/paymentPost/{paymentId}")
    public ResponseEntity<?> postPayment(@PathVariable("paymentId") String paymentId) {
        try {
            paymentsServiceM.postPayment(paymentId);
        } catch (GeneralExceptions e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Successfully Posted!");
    }

    @GetMapping("/payments/status/{paymentStatus}")
    public ResponseEntity<List<PaymentsM>> getAllPaymentByStatus(@PathVariable("paymentStatus") PaymentStatus paymentStatus) {
        List<PaymentsM> paymentByStatus = this.paymentsServiceM.findPaymentByStatus(paymentStatus);
        return ResponseEntity.ok(paymentByStatus);
    }


}
