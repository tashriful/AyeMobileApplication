package com.aye.web.model.user;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserMenuAuth {
    private String id;
    private String menuName;
    private String menuLevel;
    private Boolean isMenuActive;
    private String module;
    private List<UserMenuDetailsAuth> userMenuDetailsList = new ArrayList<>();

}
