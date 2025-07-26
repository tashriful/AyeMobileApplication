package com.aye.web.controller;

import com.aye.web.model.user.UserMenuDetailsM;
import com.aye.web.model.user.UserMenuDetailsMDto;
import com.aye.web.model.user.UserMenuM;
import com.aye.web.service.UserMenuDetailsMService;
import com.aye.web.service.UserMenuMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserMenuMController {

    @Autowired
    private UserMenuMService userMenuMService;

    @Autowired
    private UserMenuDetailsMService userMenuDetailsMService;

    @PostMapping("/saveMenu")
    public ResponseEntity<?> saveUserMenu(@RequestBody UserMenuM userMenuM){
        return ResponseEntity.ok(userMenuMService.saveUserMenu(userMenuM));
    }

    @GetMapping("/getAllMenu")
    public ResponseEntity<?> getAllUserMenu(){
        return ResponseEntity.ok(userMenuMService.getAllUserMenu());
    }

    @GetMapping("/getMenuById/{id}")
    public ResponseEntity<?> getUserMenuById(@PathVariable("id") String id){
        return ResponseEntity.ok(userMenuMService.getUserMenuById(id));
    }

    @PutMapping("/updateMenu/{id}")
    public ResponseEntity<?> updateUserMenu(@RequestBody UserMenuM userMenuM, @PathVariable("id") String id){
        return ResponseEntity.ok(userMenuMService.updateUserMenu(userMenuM));
    }

    @PostMapping("/saveMenuDetails")
    public ResponseEntity<?> saveUserMenuDetails(@RequestBody UserMenuDetailsM userMenuDetailsM){
        return ResponseEntity.ok(userMenuDetailsMService.saveUserMenuDetails(userMenuDetailsM));
//        return ResponseEntity.ok("userMenuDetailsMService.saveUserMenuDetails(userMenuDetailsM)");
    }

    @GetMapping("/getAllMenuDetails")
    public ResponseEntity<?> getAllUserMenuDetails(){
        return ResponseEntity.ok(userMenuDetailsMService.getAllUserMenuDetails());
    }

    @GetMapping("/getMenuDetailsById/{id}")
    public ResponseEntity<?> getUserMenuDetailsById(@PathVariable("id") String id){
        return ResponseEntity.ok(userMenuDetailsMService.findUserMenuDetailsById(id));
    }

    @PutMapping("/updateMenuDetails/{id}")
    public ResponseEntity<?> updateUserMenuDetails(@RequestBody UserMenuDetailsM userMenuDetailsM, @PathVariable("id") String id){
        return ResponseEntity.ok(userMenuDetailsMService.updateUserMenuDetails(userMenuDetailsM));
    }

    @GetMapping("/getMenuDetailsByUserMenu/{menuId}")
    public ResponseEntity<List<UserMenuDetailsM>> getUserMenuDetailsByUserMenu(@PathVariable("menuId") String menuId){
        UserMenuM userMenuM = userMenuMService.getUserMenuById(menuId);
        return ResponseEntity.ok(userMenuDetailsMService.findUserMenuDetailsByUserMenu(userMenuM));
    }
}
