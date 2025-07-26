package com.aye.web.model.common;

import com.aye.web.model.user.UserM;
import com.mongodb.lang.Nullable;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "ORGANIZATION_HIERARCHY_M")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class OrgHierarchyM {
    @Id
    private String id;

//    private OrgTypeM type;

    private String name;

    private String code;

    private String address;

    @DBRef
    @Nullable
    private OrgHierarchyM orgHierarchy;

    private Long parentAppOrgHierachyMId;

    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;
}
