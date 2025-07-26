package com.aye.web.controller;


import com.aye.web.model.common.CustomerLineM;
import com.aye.web.service.CustomerLineMService;
import com.aye.web.utill.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerLineMController {

    private final CustomerLineMService customerLineMService;

    @Autowired
    public CustomerLineMController(CustomerLineMService customerLineMService) {
        this.customerLineMService = customerLineMService;
    }

    @GetMapping("/customerLines/org/{orgId}")
    public ResponseEntity<?> findByOrg(@PathVariable("orgId") String orgId){
        List<CustomerLineM> customerLineByOrg = customerLineMService.findCustomerLineByOrg(orgId);
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Customer retrieved successfully", customerLineByOrg));
    }

    @GetMapping("/customerLines/org/{orgId}/{id}")
    public ResponseEntity<?> findByOrgAndId(@PathVariable("orgId") String orgId, @PathVariable("id") String id){
        return ResponseEntity.ok(
                ResponseUtils.success(HttpStatus.OK.value(), "Customer retrieved successfully", customerLineMService.findCustomerLineByOrgAndId(orgId, id)));
    }
    @GetMapping("/customerLines/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id){
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Customer retrieved successfully", customerLineMService.findCustomerLineById(id)));
    }
    @GetMapping("/searchCustomerById/{id}")
    public ResponseEntity<?> searchCustomerById(@RequestBody CustomerLineM customerLineM, @PathVariable("id") String id){
        List<CustomerLineM> customerNames = customerLineMService.findByCustomerNameLikeIgnoreCaseAndId(customerLineM.getName(), id);
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Customer retrieved successfully", customerNames)); //ResponseEntity.ok(customerNames);
    }

    @GetMapping("/searchCustomer")
    public ResponseEntity<?> searchCustomer(@RequestBody CustomerLineM customerLineM){
        List<CustomerLineM> customerNames = customerLineMService.
                findByOrgHierarchyAndName(customerLineM.getOrgHierarchy(), customerLineM.getName());
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Customer retrieved successfully", customerNames));
    }
    @GetMapping("/searchCustomerStartsWith")
    public ResponseEntity<?> searchCustomerStartsWith(@RequestBody CustomerLineM customerLineM){
        List<CustomerLineM> customerNames = customerLineMService.findByCustomerNameStartingWith(customerLineM.getName());
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Customer retrieved successfully", customerNames));
    }

    @GetMapping("/searchCustomerRegex")
    public ResponseEntity<?> searchCustomerRegex(@RequestBody CustomerLineM customerLineM){
        List<CustomerLineM> customerNames = customerLineMService.findBynameRegexIgnoreCase(customerLineM.getName());
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Customer retrieved successfully", customerNames)); //ResponseEntity.ok(customerNames);
    }
}
