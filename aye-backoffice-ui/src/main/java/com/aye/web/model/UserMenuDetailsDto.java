package com.aye.web.model;

public class UserMenuDetailsDto {
    private String id;
    private String menuName;
    private UiPathDto uiPath;
    private String pageID;
    private Boolean isActive;
    private UserMenuDto  userMenuM;

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

    public UiPathDto getUiPath() {
        return uiPath;
    }

    public void setUiPath(UiPathDto uiPath) {
        this.uiPath = uiPath;
    }

    public String getPageID() {
        return pageID;
    }

    public void setPageID(String pageID) {
        this.pageID = pageID;
    }

//    public Boolean getActive() {
//        return isActive;
//    }
//
//    public void setActive(Boolean active) {
//        isActive = active;
//    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public UserMenuDto getUserMenuM() {
        return userMenuM;
    }

    public void setUserMenuM(UserMenuDto userMenuM) {
        this.userMenuM = userMenuM;
    }
}
