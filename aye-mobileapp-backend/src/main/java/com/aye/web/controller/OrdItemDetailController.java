package com.aye.web.controller;

import com.aye.web.service.OrdItemDetailMService;
import com.aye.web.utill.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdItemDetailController {

    @Autowired
    private OrdItemDetailMService ordItemDetailMService;

    @GetMapping("/ordItemDetailInfo/org/{parentOrgId}/invOrg/{parentInvOrgId}")
    public ResponseEntity<?> findAllByInvOrgId(@PathVariable("parentOrgId") int parentOrgId,
                                               @PathVariable("parentInvOrgId") int parentInvOrgId) {
        return ResponseEntity.ok(
                ResponseUtils.success(HttpStatus.OK.value(), "Order retrieved successfully", ordItemDetailMService.findByParentOrgAndInvOrg(parentOrgId, parentInvOrgId)));
    }

}
