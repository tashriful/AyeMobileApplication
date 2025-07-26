package com.aye.web.service;


import com.aye.web.model.user.UserMenuDetailsM;
import com.aye.web.model.user.UserMenuM;
import com.aye.web.repo.user.UserMenuDetailsMRepo;
import com.aye.web.repo.user.UserMenuMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserMenuDetailsMServiceImpl implements UserMenuDetailsMService {

    @Autowired
    private UserMenuDetailsMRepo userMenuDetailsMRepo;

    @Autowired
    private UserMenuMRepo userMenuMRepo;

    @Override
    public UserMenuDetailsM saveUserMenuDetails(UserMenuDetailsM userMenuDetailsM) {
        UserMenuDetailsM savedmenuDetailsM = userMenuDetailsMRepo.save(userMenuDetailsM);

        Optional<UserMenuM> userMenuM = userMenuMRepo.findById(savedmenuDetailsM.getUserMenuM().getId());
        List<UserMenuDetailsM> userMenuDetailsMList = userMenuM.get().getUserMenuDetailsMList();
        userMenuDetailsMList.add(savedmenuDetailsM);
        userMenuM.get().setUserMenuDetailsMList(userMenuDetailsMList);
        userMenuMRepo.save(userMenuM.get());
        return savedmenuDetailsM;

//        Optional<UserMenuDetailsM> menuDetailsMOptional = userMenuDetailsMRepo.findById(savedmenuDetailsM.getId());
//        Optional<UserMenuM> userMenuM = userMenuMRepo.findById(menuDetailsMOptional.get().getUserMenuId());
//
//        List<UserMenuDetailsM> userMenuDetailsMList = userMenuM.get().getUserMenuDetailsMList();
//        userMenuDetailsMList.add(menuDetailsMOptional.get());
//        userMenuM.get().setUserMenuDetailsMList(userMenuDetailsMList);
//        userMenuMRepo.save(userMenuM.get());

//        return menuDetailsMOptional.get();
    }

    @Override
    public List<UserMenuDetailsM> getAllUserMenuDetails() {
        return userMenuDetailsMRepo.findAll();
    }

    @Override
    public UserMenuDetailsM updateUserMenuDetails(UserMenuDetailsM userMenuDetailsM) {
        return userMenuDetailsMRepo.save(userMenuDetailsM);
    }

    @Override
    public UserMenuDetailsM findUserMenuDetailsById(String id) {
        UserMenuDetailsM userMenuDetailsM = userMenuDetailsMRepo.findById(id).orElse(null);
        UserMenuDetailsM userMenuDetailsM1 = userMenuDetailsM;
        return userMenuDetailsM1;
    }

//    @Override
//    public List<UserMenuDetailsM> getAllUserMenuDetailsByMenu(UserMenuM userMenuM) {
////        return userMenuDetailsMRepo.findUserMenuDetailsMByUserMenuM(userMenuM);
//        return null;
//    }

    @Override
    public List<UserMenuDetailsM> findUserMenuDetailsByUserMenu(UserMenuM userMenuM) {
        return userMenuDetailsMRepo.findUserMenuDetailsMByUserMenuM(userMenuM);
    }
}
