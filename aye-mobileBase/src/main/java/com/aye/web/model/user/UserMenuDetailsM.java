package com.aye.web.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "USER_MENU_DETAILS_M")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserMenuDetailsM {
    @Id
    private String id;
    private String menuName;
    private String page;
    private UiPath uiPath;
    private Boolean isActive;
    @DBRef
    @JsonBackReference
    @ToString.Exclude
    private UserMenuM  userMenuM;

    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;

}
