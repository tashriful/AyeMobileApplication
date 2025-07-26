package com.aye.web.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

public class UserMenuDto {


    private String id;
    private String menuName;
    private String menuLevel;
    private Boolean isMenuActive;
    private String module;

//    @JsonManagedReference
    private List<UserMenuDetailsDto> userMenuDetailsMList = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Boolean getIsMenuActive() {
        return isMenuActive;
    }

    public void setIsMenuActive(Boolean menuActive) {
        isMenuActive = menuActive;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public List<UserMenuDetailsDto> getUserMenuDetailsMList() {
        return userMenuDetailsMList;
    }

    public void setUserMenuDetailsMList(List<UserMenuDetailsDto> userMenuDetailsMList) {
        this.userMenuDetailsMList = userMenuDetailsMList;
    }
}
