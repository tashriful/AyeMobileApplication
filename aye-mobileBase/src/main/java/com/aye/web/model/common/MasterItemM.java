package com.aye.web.model.common;

import com.aye.web.model.user.UserM;
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


@Document(collection = "INV_ITEM_M")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class MasterItemM {

    @Id
    private String id;

    private String itemCode;

    private String description;

    private String defaultUom;

    private ItemStatusM status;

    @DBRef
    private InventoryInformationsM inventoryInformationsM;

    private Long parentAppMasterItemMId;

    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;
}
