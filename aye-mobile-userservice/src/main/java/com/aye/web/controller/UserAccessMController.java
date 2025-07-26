package com.aye.web.controller;

import com.aye.web.model.user.UserAccessM;
import com.aye.web.model.user.UserM;
import com.aye.web.service.UserAccessMService;
import com.aye.web.service.UserMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAccessMController {

    @Autowired
    private UserAccessMService userAccessMService;

    @Autowired
    private UserMService userMService;

    @PostMapping("/saveUserAccess")
    public ResponseEntity<?> saveUserAccessM(@RequestBody UserAccessM userAccessM) {
        return ResponseEntity.ok(userAccessMService.saveUserAccess(userAccessM));
    }

    @GetMapping("/getAllUserAccess")
    public ResponseEntity<?> getAllUserAccess() {
        return ResponseEntity.ok(userAccessMService.getAllUserAccess());
    }

    @GetMapping("/getUserAccessById/{id}")
    public ResponseEntity<?> getUserAccessById(@PathVariable("id") String id) {
        return ResponseEntity.ok(userAccessMService.getUserAccessById(id));
    }

    @PutMapping("/updateUserAccess/{id}")
    public ResponseEntity<?> updateUserAccess(@RequestBody UserAccessM userAccessM,
                                              @PathVariable("id") String id) {
        return ResponseEntity.ok(userAccessMService.updateUserAccess(userAccessM));
    }

    @GetMapping("/getUserAccess/user/{id}")
    public ResponseEntity<?> getUserAccessByUser(@PathVariable("id") String id){
        UserM userM = userMService.findUserMById(id);
        return ResponseEntity.ok(userAccessMService.getAllUserAccessByUser(userM));
    }

}
