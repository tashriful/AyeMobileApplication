package com.aye.web.model.order;


import com.aye.web.model.common.InventoryInformationsM;
import com.aye.web.model.common.OrgHierarchyM;
import com.aye.web.model.user.UserM;
import com.mongodb.lang.Nullable;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "ORD_ORDER_TRANS_TYPE_M")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class OrderTrnsTypeM {

    @Id
    private String id;
    private String trnsTypeName;
    private String description;
    private Boolean active;
    @DBRef
    private OrgHierarchyM orgHierarchy;
    @DBRef
    private InventoryInformationsM invOrg;
    @Nullable
    private Long parentAppTrnsTypeMId;
    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;


}
