package com.aye.web.controller;

import com.aye.web.model.user.UserAccessTemplateDetailsM;
import com.aye.web.model.user.UserAccessTemplateM;
import com.aye.web.service.UserAccessTemplateDetailsMService;
import com.aye.web.service.UserAccessTemplateMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccessTemplateController {

    @Autowired
    private UserAccessTemplateMService userAccessTemplateMService;

    @Autowired
    private UserAccessTemplateDetailsMService userAccessTemplateDetailsMService;

    @PostMapping("/saveAccessTemplate")
    public ResponseEntity<?> saveAccessTemplate(@RequestBody UserAccessTemplateM userAccessTemplateM){
        return ResponseEntity.ok(userAccessTemplateMService.saveAccessTemplate(userAccessTemplateM));
    }

    @GetMapping("/getAllAccessTemplate")
    public ResponseEntity<?> getAllAccessTemplate(){
        return ResponseEntity.ok(userAccessTemplateMService.getAllAccessTemplate());
    }

    @GetMapping("/getAccessTemplateById/{id}")
    public ResponseEntity<?> getAccessTemplateById(@PathVariable("id") String id){
        return ResponseEntity.ok(userAccessTemplateMService.findAccessTemplateById(id));
    }

    @PutMapping("/updateAccessTemplate/{id}")
    public ResponseEntity<?> updateAccessTemplate(@RequestBody UserAccessTemplateM userAccessTemplateM,
                                                    @PathVariable("id") String id){
        return ResponseEntity.ok(userAccessTemplateMService.updateAccessTemplate(userAccessTemplateM));
    }

    @PostMapping("/saveAccessTemplateDetails")
    public ResponseEntity<?> saveAccessTemplateDetails(@RequestBody UserAccessTemplateDetailsM userAccessTemplateDetailsM){
        return ResponseEntity.ok(userAccessTemplateDetailsMService.saveTemplateDetails(userAccessTemplateDetailsM));
    }

    @GetMapping("/getAllAccessTemplateDetails")
    public ResponseEntity<?> getAllAccessTemplateDetails(){
        return ResponseEntity.ok(userAccessTemplateDetailsMService.getAllTemplateDetails());
    }

    @GetMapping("/getAccessTemplateDetailsById/{id}")
    public ResponseEntity<?> getAccessTemplateDetailsById(@PathVariable("id") String id){
        return ResponseEntity.ok(userAccessTemplateDetailsMService.findTemplateDetailsById(id));
    }

    @PutMapping("/updateAccessTemplateDetails/{id}")
    public ResponseEntity<?> updateAccessTemplateDetails(@RequestBody UserAccessTemplateDetailsM userAccessTemplateDetailsM,
                                                         @PathVariable("id") String id){
        return ResponseEntity.ok(userAccessTemplateDetailsMService.updateTemplateDetails(userAccessTemplateDetailsM));
    }

    @GetMapping("/getTmpltDetailsByTmplt/accessTemplate/{templateId}")
    public ResponseEntity<?> findTmpltDetailsByTmplt(@PathVariable("templateId") String templateId){
        UserAccessTemplateM accessTemplateById = userAccessTemplateMService.findAccessTemplateById(templateId);
        return ResponseEntity.ok(userAccessTemplateDetailsMService.findTmpltDetailsByTmplt(accessTemplateById));
    }





}
