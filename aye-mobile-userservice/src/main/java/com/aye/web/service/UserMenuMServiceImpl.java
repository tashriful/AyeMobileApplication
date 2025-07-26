package com.aye.web.service;


import com.aye.web.model.user.UserMenuM;
import com.aye.web.repo.user.UserMenuMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMenuMServiceImpl implements UserMenuMService {

    @Autowired
    private UserMenuMRepo userMenuMRepo;

    @Autowired
    private UserMenuDetailsMService userMenuDetailsMService;

    @Override
    public UserMenuM saveUserMenu(UserMenuM userMenuM) {
        return userMenuMRepo.save(userMenuM);
    }

    @Override
    public List<UserMenuM> getAllUserMenu() {
        return userMenuMRepo.findAll();
    }

    @Override
    public UserMenuM updateUserMenu(UserMenuM userMenuM) {
        return userMenuMRepo.save(userMenuM);
    }

    @Override
    public UserMenuM getUserMenuById(String id) {
        UserMenuM userMenuM = userMenuMRepo.findById(id).orElse(null);
//        if (userMenuM != null) {
//            userMenuM.setUserMenuDetailsMList(userMenuDetailsMService.findUserMenuDetailsByUserMenu(userMenuM));
//        }
        return userMenuM;

    }
}
