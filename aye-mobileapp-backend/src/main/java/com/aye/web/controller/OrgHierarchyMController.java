package com.aye.web.controller;


import com.aye.web.model.common.OrgHierarchyM;
import com.aye.web.service.OrgHierarchyMService;
import com.aye.web.utill.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrgHierarchyMController {

    private OrgHierarchyMService orgHierarchyMService;

    @Autowired
    public OrgHierarchyMController(OrgHierarchyMService orgHierarchyMService) {
        this.orgHierarchyMService = orgHierarchyMService;
    }

    @PostMapping("/orgHierarchy")
    public ResponseEntity<?> saveOrgHierarchyM(@RequestBody OrgHierarchyM orgHierarchyM){
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "OrgHierarchy saved successfully", orgHierarchyMService.saveOrgHierarchyM(orgHierarchyM)));
    }

    @GetMapping("/orgHierarchy")
    public ResponseEntity<?> getAllOrgHierarchy(){
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "OrgHierarchy retrieved successfully", orgHierarchyMService.findAllOrgHierarchyM()));
    }

    @GetMapping("/orgHierarchy/{id}")
    public ResponseEntity<?> getAllOrgHierarchyById(@PathVariable("id") String id){
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "OrgHierarchy retrieved successfully", (OrgHierarchyM)orgHierarchyMService.findOrgHierarchyMById(id)));
    }
}
