package com.aye.web.model.user;

import com.aye.web.model.common.OrgHierarchyM;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Document(collection = "USER_ACCESS_TEMPLATE_DTL_M")
public class UserAccessTemplateDetailsM {

    @Id
    private String id;
    private String tmplDetailName;
    @DBRef
    private OrgHierarchyM orgHierarchyM;
    @DBRef
    private UserMenuM userMenuM;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    @DBRef
    @JsonBackReference
    @ToString.Exclude
    private UserAccessTemplateM userAccessTemplateM;

    @DBRef
    @JsonManagedReference
    private List<UserOrgAccessM> userOrgAccessMList;

    @DBRef
    @JsonManagedReference
    private List<UserInvOrgAccessM> userInvOrgAccessMList;
    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;
}
