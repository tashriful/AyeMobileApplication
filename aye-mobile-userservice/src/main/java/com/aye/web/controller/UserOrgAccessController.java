package com.aye.web.controller;

import com.aye.web.model.user.UserOrgAccessM;
import com.aye.web.service.UserOrgAccessMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserOrgAccessController {

    @Autowired
    private UserOrgAccessMService userOrgAccessMService;

    @PostMapping("/saveUserOrgAccess")
    private ResponseEntity<?> saveUserOrgAccess(@RequestBody UserOrgAccessM userOrgAccessM){
        return ResponseEntity.ok(userOrgAccessMService.saveUserOrgAccess(userOrgAccessM));
    }

    @GetMapping("/getAllUserOrgAccess")
    public ResponseEntity<?> getAllUserOrgAccess(){
        return ResponseEntity.ok(userOrgAccessMService.getAllUserOrgAccess());
    }

    @GetMapping("/getUserOrgAccessById/{id}")
    public ResponseEntity<?> getUserOrgAccessById(@PathVariable("id") String id){
        return ResponseEntity.ok(userOrgAccessMService.getUserOrgAccessById(id));
    }

    @PutMapping("/updateUserOrgAccess/{id}")
    public ResponseEntity<?> updateUserOrgAccess(@RequestBody UserOrgAccessM userOrgAccessM,
                                                 @PathVariable("id") String id){
        return ResponseEntity.ok(userOrgAccessMService.updateUserOrgAccess(userOrgAccessM));
    }
}
