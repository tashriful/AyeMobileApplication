package com.aye.web.model.user;

import com.aye.web.model.common.InventoryInformationsM;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "USER_INV_ORG_ACCESS_M")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserInvOrgAccessM {
    @Id
    private String id;
    @DBRef
    private InventoryInformationsM inventoryInformationsM;
    @DBRef
    @JsonBackReference
    @ToString.Exclude
    private UserAccessTemplateDetailsM userAccessTemplateDetails;
    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;


}
