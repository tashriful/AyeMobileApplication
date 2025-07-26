package com.aye.web.controller;

import com.aye.web.model.user.UserInvOrgAccessM;
import com.aye.web.service.UserInvOrgAccessMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserInvOrgAccessMController {

    @Autowired
    private UserInvOrgAccessMService userInvOrgAccessMService;

    @PostMapping("/saveUserInvOrgAccess")
    public ResponseEntity<?> saveUserInvOrgAccess(@RequestBody UserInvOrgAccessM userInvOrgAccessM){
        return ResponseEntity.ok(userInvOrgAccessMService.saveUserInvOrgAccess(userInvOrgAccessM));
    }

    @GetMapping("/getAllUserInvOrgAccess")
    public ResponseEntity<?> getAllUserInvOrgAccess(){
        return ResponseEntity.ok(userInvOrgAccessMService.getAllUserInvOrgAccess());
    }

    @GetMapping("/getAllUserInvOrgAccessByTempDtl/{tempDtlId}")
    public ResponseEntity<?> getAllUserInvOrgAccessByTemptDtl(@PathVariable("tempDtlId") String tempDtlId){
        return ResponseEntity.ok(userInvOrgAccessMService.getAllInvOrgAccessByTmpltDtl(tempDtlId));
    }

    @GetMapping("/getUserInvOrgAccessById/{id}")
    public ResponseEntity<?> getUserInvOrgAccessById(@PathVariable("id") String id){
        return ResponseEntity.ok(userInvOrgAccessMService.getUserInvOrgAccessById(id));
    }

}
