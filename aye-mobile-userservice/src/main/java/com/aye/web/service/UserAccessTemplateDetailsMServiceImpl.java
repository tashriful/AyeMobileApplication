package com.aye.web.service;

import com.aye.web.model.user.UserAccessTemplateDetailsM;
import com.aye.web.model.user.UserAccessTemplateM;
import com.aye.web.repo.user.UserAccessMRepo;
import com.aye.web.repo.user.UserAccessTemplateDetailsMRepo;
import com.aye.web.repo.user.UserAccessTemplateMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccessTemplateDetailsMServiceImpl implements UserAccessTemplateDetailsMService {

    @Autowired
    private UserAccessTemplateDetailsMRepo userAccessTemplateDetailsMRepo;
    @Autowired
    private UserAccessMRepo userAccessMRepo;
    @Autowired
    private UserAccessTemplateMRepo userAccessTemplateMRepo;

    @Override
    public UserAccessTemplateDetailsM saveTemplateDetails(UserAccessTemplateDetailsM userAccessTemplateDetailsM) {
        UserAccessTemplateDetailsM templateDetailsM = userAccessTemplateDetailsMRepo.save(userAccessTemplateDetailsM);

        Optional<UserAccessTemplateM> userAccessTemplateM = userAccessTemplateMRepo.
                findById(templateDetailsM.getUserAccessTemplateM().getId());
        List<UserAccessTemplateDetailsM> userAccessTemplateDetailsMList = new ArrayList<>();

        if(userAccessTemplateM.get().getUserAccessTemplateDetailsMList() != null){
            userAccessTemplateDetailsMList = userAccessTemplateM.get().getUserAccessTemplateDetailsMList();
        }
        userAccessTemplateDetailsMList.add(templateDetailsM);


        userAccessTemplateM.get().setUserAccessTemplateDetailsMList(userAccessTemplateDetailsMList);
        userAccessTemplateMRepo.save(userAccessTemplateM.get());

        return templateDetailsM;


    }

    @Override
    public List<UserAccessTemplateDetailsM> getAllTemplateDetails() {
        return userAccessTemplateDetailsMRepo.findAll();
    }

    @Override
    public UserAccessTemplateDetailsM updateTemplateDetails(UserAccessTemplateDetailsM userAccessTemplateDetailsM) {
        return userAccessTemplateDetailsMRepo.save(userAccessTemplateDetailsM);
    }

    @Override
    public UserAccessTemplateDetailsM findTemplateDetailsById(String id) {
        return userAccessTemplateDetailsMRepo.findById(id).orElse(null);
    }

    @Override
    public List<UserAccessTemplateDetailsM> findTmpltDetailsByTmplt(UserAccessTemplateM userAccessTemplateM) {
        return userAccessTemplateDetailsMRepo.findUserAccessTemplateDetailsMByUserAccessTemplateMId(userAccessTemplateM.getId());
    }
}
