package com.aye.web.model.schedule;

import com.aye.web.model.common.InventoryInformationsM;
import com.aye.web.model.common.OrgHierarchyM;
import com.aye.web.model.order.OrderTrnsTypeM;
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

@Document(collection = "ORD_DELV_SCHEDULE_HEADER_M")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrdDelvScheduleHeaderM {

    @Id
    private String id;

    private String scheduleNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private ScdLineStatusM status;
    @DBRef
    private OrgHierarchyM orgHierarchy;
    @DBRef
    private OrderTrnsTypeM orderTrnsType;
    @DBRef
    private InventoryInformationsM inventoryInformations;

    @DBRef
    @JsonManagedReference
    private List<OrdDelvScheduleLineM> ordDelvScheduleLines;

    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;

}
