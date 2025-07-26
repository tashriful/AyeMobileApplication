package com.aye.web.service;


import com.aye.web.model.user.UserAccessM;
import com.aye.web.model.user.UserM;
import com.aye.web.model.user.UserRequestDto;
import com.aye.web.repo.user.UserMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserMServiceImpl implements UserMService {

    private final UserMRepo userMRepo;
    private final RestTemplate restTemplate;

    private final UserAccessMService userAccessMService;
    private final UserAccessTemplateDetailsMService userAccessTemplateDetailsMService;
    private final UserMenuDetailsMService userMenuDetailsMService;
    private final PasswordEncoder passwordEncoder;
//    private final RestTemplate restTemplate;

    @Autowired
    public UserMServiceImpl(UserMRepo userMRepo, RestTemplate restTemplate, UserAccessMService userAccessMService, UserAccessTemplateDetailsMService userAccessTemplateDetailsMService, UserMenuDetailsMService userMenuDetailsMService, PasswordEncoder passwordEncoder) {
        this.userMRepo = userMRepo;
        this.restTemplate = restTemplate;
        this.userAccessMService = userAccessMService;
        this.userAccessTemplateDetailsMService = userAccessTemplateDetailsMService;
        this.userMenuDetailsMService = userMenuDetailsMService;
//        this.restTemplate = restTemplate;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserM saveUserM(UserM userM) {
        if (userM.getId() == null) {
            // ========== CREATE ==========
            userM.setPassword(passwordEncoder.encode(userM.getPassword()));
            return userMRepo.save(userM);
        } else {
            // ========== UPDATE ==========
            UserM existingUser = userMRepo.findById(userM.getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Update fields
            existingUser.setName(userM.getName());
            existingUser.setUsername(userM.getUsername());
            existingUser.setDesignation(userM.getDesignation());
            existingUser.setActive(userM.isActive());

            // Only update password if changed (optional)
            if (userM.getPassword() != null && !userM.getPassword().isBlank()) {
                existingUser.setPassword(passwordEncoder.encode(userM.getPassword()));
            } else {
                existingUser.setPassword(existingUser.getPassword());
            }

            // Set audit/update timestamps if needed


            return userMRepo.save(existingUser);
        }
    }

    @Override
    public List<UserM> getAllUserM() {

        return userMRepo.findAll();
    }

    @Override
    public UserM findUserMById(String id) {
        return userMRepo.findById(id).orElse(null);
    }

    @Override
    public UserM updateUserM(UserM userM) {
        return userMRepo.save(userM);
    }

    @Override
    public void authenticateUser(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        UserM userM = userMRepo.findUserMByUsername(username);
        List<UserAccessM> userAccessMList = userAccessMService.getAllUserAccessByUser(userM);
        System.out.println(userAccessMList);


//      userAccessMList.stream().map(ua->{
//          List<UserAccessTemplateDetailsM> userAccessTemplateDetailsMList =
//                  userAccessTemplateDetailsMService.findTmpltDetailsByTmplt(ua.getUserAccessTemplateM());
//
//
//          userAccessTemplateDetailsMList.stream().map(td-> {
//
//              UserMenuM userMenuM = td.getUserMenuM();
//              List<UserMenuDetailsM> userMenuDetailsMList = userMenuDetailsMService.getAllUserMenuDetailsByMenu(userMenuM);
//
//
//          })
//      })


    }

    @Override
    public UserM findUserMByUserName(String username) {
        return userMRepo.findUserMByUsername(username);
    }
}
