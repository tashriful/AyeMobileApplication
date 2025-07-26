package com.aye.web.model.order;


import com.aye.web.model.CustomerLineMDto;
import com.aye.web.model.InventoryInformationsMDto;
import com.aye.web.model.OrgHierarchyMDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderHeaderMDto {
    private String id;
    private String orderNumber;
    private OrderStatusMDto orderStatus;
    private String notes;
    private OrderTrnsTypeMDto orderTrnsType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    private OrgHierarchyMDto orgHierarchy;

    private InventoryInformationsMDto inventoryInformations;

    private CustomerLineMDto customerLine;

    @JsonManagedReference
    private List<OrderLineMDto> orderLineMList;



}
