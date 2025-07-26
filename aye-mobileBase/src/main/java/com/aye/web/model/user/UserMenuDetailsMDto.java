package com.aye.web.model.user;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserMenuDetailsMDto {
    private String id;
    private String menuName;
    private UiPath uiPath;
    private boolean isActive;

    private UserMenuM  userMenuM;


    private UserM createdBy;

    private Date cretedAt;

    private UserM updatedBy;

    private Date updatedAt;

}
