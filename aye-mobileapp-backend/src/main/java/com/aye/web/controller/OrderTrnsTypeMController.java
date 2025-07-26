package com.aye.web.controller;

import com.aye.web.dto.ApiResponse;
import com.aye.web.service.OrderTrnsTypeMService;
import com.aye.web.model.order.OrderTrnsTypeM;
import com.aye.web.utill.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderTrnsTypeMController {

    private final OrderTrnsTypeMService orderTrnsTypeMService;

    @Autowired
    public OrderTrnsTypeMController(OrderTrnsTypeMService orderTrnsTypeMService) {
        this.orderTrnsTypeMService = orderTrnsTypeMService;
    }

    @GetMapping("/transactionTypes/org/{orgId}/invOrg/{invOrgId}")
    public ResponseEntity<ApiResponse<List<OrderTrnsTypeM>>> getTransactionByOrgAndInvOrg(@PathVariable("orgId") String orgId,
                                                                                          @PathVariable("invOrgId") String invOrgId){

        List<OrderTrnsTypeM> allOrderTrnsTypeByOrgAndInvOrg = orderTrnsTypeMService.findAllOrderTrnsTypeByOrgAndInvOrg(orgId, invOrgId);
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Transaction retrieved successfully", allOrderTrnsTypeByOrgAndInvOrg));
    }


}
