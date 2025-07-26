package com.aye.web.controller;


import com.aye.web.service.BankLineMService;
import com.aye.web.utill.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankLineMController {
    private final BankLineMService bankLineMService;

    @Autowired
    public BankLineMController(BankLineMService bankLineMService) {
        this.bankLineMService = bankLineMService;
    }

    @GetMapping("/bankLines/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id){
        return ResponseEntity.ok(
                ResponseUtils.success(HttpStatus.OK.value(), "Bank retrieved successfully", bankLineMService.findBankById(id)));
    }

    @GetMapping("/banklines/org/{orgId}/{bankLineId}")
    public ResponseEntity<?> findByOrgAndId(@PathVariable("orgId") String orgId, @PathVariable("bankLineId") String id){
        return ResponseEntity.ok(ResponseUtils.success(
                HttpStatus.OK.value(), "Bank retrieved successfully", bankLineMService.findBankLineByOrgAndId(orgId, id)));
    }

    @GetMapping("/bankLines/org/{orgId}")
    public ResponseEntity<?> findByOrg(@PathVariable("orgId") String orgId){
        return ResponseEntity.ok(
                ResponseUtils.success(HttpStatus.OK.value(), "Bank retrieved successfully", bankLineMService.findAllBankByOrg(orgId)));
    }
}
