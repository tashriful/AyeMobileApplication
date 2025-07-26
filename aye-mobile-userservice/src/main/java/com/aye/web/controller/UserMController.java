package com.aye.web.controller;

import com.aye.web.model.user.UserM;
import com.aye.web.model.user.UserRequestDto;
import com.aye.web.service.UserAccessMService;
import com.aye.web.service.UserMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserMController {

    @Autowired
    private UserMService userMService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAccessMService userAccessMService;


    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody UserM userM){
        if(userM.getId() == null) {
            userM.setPassword(passwordEncoder.encode(userM.getPassword()));
        }
        return ResponseEntity.ok(userMService.saveUserM(userM));
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok(userMService.getAllUserM());
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String id){
        return ResponseEntity.ok(userMService.findUserMById(id));
    }

    @GetMapping("/getUserByUserName/{username}")
    public ResponseEntity<?> getUserByUserName(@PathVariable("username") String username){
        return ResponseEntity.ok(userMService.findUserMByUserName(username));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserM userM, @PathVariable("id") String id){
        return ResponseEntity.ok(userMService.updateUserM(userM));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody UserRequestDto userRequestDto){
        userMService.authenticateUser(userRequestDto);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/getUserAccessMenusByUserName/{username}")
    public ResponseEntity<?> getUserAccessByUser(@PathVariable("username") String username){
        UserM userM = userMService.findUserMByUserName(username);
        return ResponseEntity.ok(userAccessMService.getAllUserAccessMenusByUser(userM));
    }
}
