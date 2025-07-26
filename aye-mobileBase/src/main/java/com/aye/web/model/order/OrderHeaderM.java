package com.aye.web.model.order;

import com.aye.web.model.common.CustomerLineM;
import com.aye.web.model.common.InventoryInformationsM;
import com.aye.web.model.common.OrgHierarchyM;
import com.aye.web.model.user.UserM;
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

@Document(collection = "ORD_ORDER_HEADER_M")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderHeaderM {
    @Id
    private String id;
    private String orderNumber;
    private OrderStatusM orderStatus;
    private String notes;
    @DBRef
    private OrderTrnsTypeM orderTrnsType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    @DBRef
    private OrgHierarchyM orgHierarchy;

    @DBRef
    private InventoryInformationsM inventoryInformations;

    @DBRef
    private CustomerLineM customerLine;

    @DBRef
    @JsonManagedReference
    private List<OrderLineM> orderLineMList;

    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;


}
