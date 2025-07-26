package com.aye.web.model.order;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class OrderHeaderMSearchDto {
    private String id;
    private String orderNumber;
    private String  orderTrnsType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;
    private String orgHierarchy;
    private String inventoryInformations;
    private String customerLine;
    private String orderStatus;
    private String notes;
    private Long userId;

    private List<OrderLineM> orderLineMList;

    public OrderHeaderMSearchDto() {
    }

    public OrderHeaderMSearchDto(String id, String orderNumber, String orderTrnsType, Date orderDate, String orgHierarchy, String inventoryInformations, String customerLine, String orderStatus, String notes, Long userId, List<OrderLineM> orderLineMList) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderTrnsType = orderTrnsType;
        this.orderDate = orderDate;
        this.orgHierarchy = orgHierarchy;
        this.inventoryInformations = inventoryInformations;
        this.customerLine = customerLine;
        this.orderStatus = orderStatus;
        this.notes = notes;
        this.userId = userId;
        this.orderLineMList = orderLineMList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderTrnsType() {
        return orderTrnsType;
    }

    public void setOrderTrnsType(String orderTrnsType) {
        this.orderTrnsType = orderTrnsType;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrgHierarchy() {
        return orgHierarchy;
    }

    public void setOrgHierarchy(String orgHierarchy) {
        this.orgHierarchy = orgHierarchy;
    }

    public String getInventoryInformations() {
        return inventoryInformations;
    }

    public void setInventoryInformations(String inventoryInformations) {
        this.inventoryInformations = inventoryInformations;
    }

    public String getCustomerLine() {
        return customerLine;
    }

    public void setCustomerLine(String customerLine) {
        this.customerLine = customerLine;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderLineM> getOrderLineMList() {
        return orderLineMList;
    }

    public void setOrderLineMList(List<OrderLineM> orderLineMList) {
        this.orderLineMList = orderLineMList;
    }

    @Override
    public String toString() {
        return "OrderHeaderSearchDto{" +
                "id='" + id + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderTrnsType='" + orderTrnsType + '\'' +
                ", orderDate=" + orderDate +
                ", orgHierarchy='" + orgHierarchy + '\'' +
                ", inventoryInformations='" + inventoryInformations + '\'' +
                ", customerLine='" + customerLine + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", notes='" + notes + '\'' +
                ", userId=" + userId +
                ", orderLineMList=" + orderLineMList +
                '}';
    }
}
