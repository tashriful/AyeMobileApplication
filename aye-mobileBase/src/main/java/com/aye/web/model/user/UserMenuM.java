package com.aye.web.model.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "USER_MENU_M")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserMenuM {
    @Id
    private String id;
    private String menuName;
    private String menuLevel;
    private Boolean isMenuActive;
    private String module;
    @DBRef
    @JsonManagedReference
    private List<UserMenuDetailsM> userMenuDetailsMList = new ArrayList<>();

    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;


}
