package com.aye.web.service;


import com.aye.web.dto.ApiResponse;
import com.aye.web.model.common.InventoryInformationsAuth;
import com.aye.web.model.common.InventoryInformationsM;
import com.aye.web.model.common.OrgHierarchyM;
import com.aye.web.model.order.OrderTrnsTypeM;
import com.aye.web.model.user.*;
import com.aye.web.repo.user.UserAccessMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAccessMServiceImpl implements UserAccessMService {

    @Autowired
    private UserAccessMRepo userAccessMRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserAccessM saveUserAccess(UserAccessM userAccessM) {
        return userAccessMRepo.save(userAccessM);
    }

    @Override
    public UserAccessM getUserAccessById(String id) {
        return userAccessMRepo.findById(id).orElse(null);
    }

    @Override
    public List<UserAccessM> getAllUserAccess() {
        return userAccessMRepo.findAll();
    }

    @Override
    public UserAccessM updateUserAccess(UserAccessM userAccessM) {
        return userAccessMRepo.save(userAccessM);
    }

    @Override
    public List<UserAccessM> getAllUserAccessByUser(UserM userM) {
        List<UserAccessM> userAccessMByUser = userAccessMRepo.findUserAccessMByUser(userM);
        return userAccessMByUser;
    }

    @Override
    public List<UserMenuAuth> getAllUserAccessMenusByUser(UserM userM) {
        List<UserAccessM> userAccessMByUser = userAccessMRepo.findUserAccessMByUser(userM);
        List<UserMenuAuth> userMenuAuthList = new ArrayList<>();

        for (UserAccessM u : userAccessMByUser) {
            UserAccessTemplateM userAccessTemplateM = u.getUserAccessTemplateM();
            List<UserAccessTemplateDetailsM> userAccessTemplateDetailsMList = userAccessTemplateM.getUserAccessTemplateDetailsMList();

            for (UserAccessTemplateDetailsM ud : userAccessTemplateDetailsMList) {
                OrgHierarchyM orgHierarchyM = ud.getOrgHierarchyM();
                List<UserInvOrgAccessM> userInvOrgAccessMList = ud.getUserInvOrgAccessMList();
                UserMenuM userMenuM = ud.getUserMenuM();
                List<UserMenuDetailsM> userMenuDetailsList = userMenuM.getUserMenuDetailsMList();
                List<UserMenuDetailsAuth> userMenuDetailsAuthList = new ArrayList<>();

                UserMenuAuth userMenuAuth = new UserMenuAuth();
                userMenuAuth.setMenuName(userMenuM.getMenuName());
                userMenuAuth.setIsMenuActive(userMenuM.getIsMenuActive());
                userMenuAuth.setMenuLevel(userMenuM.getMenuLevel());
                userMenuAuth.setModule(userMenuM.getModule());

                for (UserMenuDetailsM md : userMenuDetailsList) {
                    UserMenuDetailsAuth userMenuDetailsAuth = new UserMenuDetailsAuth();
                    userMenuDetailsAuth.setMenuName(md.getMenuName());
                    userMenuDetailsAuth.setUiPath(md.getUiPath());
                    userMenuDetailsAuth.setActive(md.getIsActive());
                    userMenuDetailsAuth.setOrgHierarchyM(OrgHierarchyM.builder().id(orgHierarchyM.getId())
                            .name(orgHierarchyM.getName()).address(orgHierarchyM.getAddress())
                            .code(orgHierarchyM.getCode())
                            .parentAppOrgHierachyMId(orgHierarchyM.getParentAppOrgHierachyMId())
                            .build());
                    if (userInvOrgAccessMList != null) {
                        userMenuDetailsAuth.setInvOrgList(userInvOrgAccessMList.stream()
                                .map(i -> {

                                    List<OrderTrnsTypeM> orderTrnsTypeList = findOrderTrnsTypeByInvOrg(orgHierarchyM.getId(), i.getInventoryInformationsM().getId());
                                    return InventoryInformationsAuth.builder().id(i.getInventoryInformationsM().getId())
                                            .name(i.getInventoryInformationsM().getName())
                                            .code(i.getInventoryInformationsM().getCode())
                                            .address(i.getInventoryInformationsM().getAddress())
                                            .parentAppInvInfoMId(i.getInventoryInformationsM().getParentAppInvInfoMId())
                                            .orderTrnsTypeList(orderTrnsTypeList != null ? orderTrnsTypeList.stream().map(or -> OrderTrnsTypeM.builder().trnsTypeName(or.getTrnsTypeName())
                                                    .id(or.getId())
                                                    .description(or.getDescription())
                                                    .active(or.getActive())
                                                    .parentAppTrnsTypeMId(or.getParentAppTrnsTypeMId())
                                                    .build()).collect(Collectors.toList()) : null)
                                            .build();
                                }).collect(Collectors.toList()));
                    }

                    userMenuDetailsAuthList.add(userMenuDetailsAuth);


                }
                userMenuAuth.setUserMenuDetailsList(userMenuDetailsAuthList);

                userMenuAuthList.add(userMenuAuth);

            }


        }

        System.out.println(userMenuAuthList);

        return userMenuAuthList;

    }

    private List<OrderTrnsTypeM> findOrderTrnsTypeByInvOrg(String orgId, String invOrgId) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = "http://MAIN-ORDER-SERVICE/msales/api/main" + "/transactionTypes/org/" + orgId + "/invOrg/" + invOrgId;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OrderTrnsTypeM> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<ApiResponse<List<OrderTrnsTypeM>>> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, new ParameterizedTypeReference<ApiResponse<List<OrderTrnsTypeM>>>() {
        });
        if (response.getBody() != null) {
            return response.getBody().getResponseData();
        } else return null;

    }
}
