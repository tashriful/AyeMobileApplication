package com.aye.web.model.user;

import com.aye.web.model.common.OrgHierarchyM;
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

@Document(collection = "USER_ORG_ACCESS_M")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserOrgAccessM{
    @Id
    private String id;
    @DBRef
    private OrgHierarchyM orgHierarchyM;
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
