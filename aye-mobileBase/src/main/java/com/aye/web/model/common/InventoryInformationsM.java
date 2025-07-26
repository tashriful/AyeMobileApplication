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

@Document(collection = "INV_INFORMATIONS_M")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class InventoryInformationsM {

    @Id
    private String id;

    private String code;

    private String name;

    private String address;

    @DBRef
    private OrgHierarchyM orgHierarchy;

    @DBRef
    @Nullable
    private InventoryInformationsM itemOrg;

    private Boolean isItemMaster;

    private Long batchCirculationLoop;

    private Long parentAppInvInfoMId;

    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;
}
