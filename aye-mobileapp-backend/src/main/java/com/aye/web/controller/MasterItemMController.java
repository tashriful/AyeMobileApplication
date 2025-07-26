package com.aye.web.controller;


import com.aye.web.model.common.MasterItemM;
import com.aye.web.service.MasterItemMService;
import com.aye.web.utill.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/masterItem")
public class MasterItemMController {

    private final MasterItemMService masterItemMService;

    @Autowired
    public MasterItemMController(MasterItemMService masterItemMService) {
        this.masterItemMService = masterItemMService;
    }

    @GetMapping("item/invOrg/{invOrgId}")
    public ResponseEntity<?> findAllByInvOrgId(@PathVariable("invOrgId") String invOrgId) {
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Item retrieved successfully", masterItemMService.findByInvOrg(invOrgId)));
    }

    @GetMapping("/item/invOrg/{invOrgId}/{id}")
    public ResponseEntity<?> findAllByInvOrgIdAndId(@PathVariable("invOrgId") String invOrgId,
                                                    @PathVariable("id") String id) {
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Item retrieved successfully", masterItemMService.findByInvOrgAndId(invOrgId, id)));
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Item retrieved successfully", masterItemMService.findById(id)));
    }

    @GetMapping("/searchItemCode")
    public ResponseEntity<?> findById(@RequestBody MasterItemM masterItemM) {
        return ResponseEntity.ok(
                ResponseUtils.success(HttpStatus.OK.value(), "Item retrieved successfully", masterItemMService.findByInvOrgAndItemCode(masterItemM.getInventoryInformationsM().getId()
                , masterItemM.getItemCode())));
    }
}


