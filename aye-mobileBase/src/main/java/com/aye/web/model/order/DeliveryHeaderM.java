package com.aye.web.model.order;

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

@Document(collection = "ORD_DELIVERY_HEADER_M")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class DeliveryHeaderM {
    @Id
    private Long headerId;
    private String delvNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date delvDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date delvConfDate;
    @DBRef
    private OrgHierarchyM orgHierarchy;
    @DBRef
    private InventoryInformationsM invOrg;
    private String vehicleNo;
    private String driverName;
    private String driverPhone;
    @DBRef
    private DeliveryStatusM deliveryStatus;
    private String notes;
    @DBRef
    @JsonManagedReference
    private List<DeliveryLineM> deliveryLines;
    private Long parentAppDeliveryHeaderId;
    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;

}
