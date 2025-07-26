package com.aye.web.controller;


import com.aye.web.model.common.InventoryInformationsM;
import com.aye.web.service.InventoryInformationsMService;
import com.aye.web.utill.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryInformationsMController {

    private InventoryInformationsMService inventoryInformationsMService;

    @Autowired
    public InventoryInformationsMController(InventoryInformationsMService inventoryInformationsMService) {
        this.inventoryInformationsMService = inventoryInformationsMService;
    }

    @PostMapping("/inventoryInformations")
    public ResponseEntity<?> saveInventoryInf(@RequestBody InventoryInformationsM inventoryInformationsM){
        return ResponseEntity.ok((ResponseUtils.success(
                HttpStatus.OK.value(), "Inventory saved successfully", inventoryInformationsMService.saveInventoryInformationsM(inventoryInformationsM))));
    }

    @GetMapping("/inventoryInformations")
    public ResponseEntity<?> getAllInventory(){
        return ResponseEntity.ok(
                ResponseUtils.success(HttpStatus.OK.value(), "Inventory retrieved successfully", inventoryInformationsMService.findAllInventoryInformationsM()));
    }

    @GetMapping("/inventoryInformations/{id}")
    public ResponseEntity<?> getInventoryById(@PathVariable("id") String id){
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Inventory retrieved successfully", inventoryInformationsMService.findInventoryInformationsMById(id)));
    }

    @GetMapping("/inventoryInformations/org/{orgId}")
    public ResponseEntity<?> getInventoriesByOrg(@PathVariable("orgId") String orgId){
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Inventory retrieved successfully", inventoryInformationsMService.findInventoriesByOrg(orgId)));
    }
}
