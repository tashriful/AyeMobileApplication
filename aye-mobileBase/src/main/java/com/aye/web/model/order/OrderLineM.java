package com.aye.web.model.order;

import com.aye.web.model.common.MasterItemM;
import com.aye.web.model.user.UserM;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "ORD_ORDER_LINE_M")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderLineM {
    @Id
    private String id;
    private String orderUomCode;
    private String itemCode;
    private BigDecimal orderUomQty;
    private BigDecimal orderDefaultUomQty;
    private BigDecimal convRate;
    private BigDecimal price;
    private BigDecimal amount;
    private String lineNotes;
    @DBRef
    @JsonBackReference
    @ToString.Exclude
    private OrderHeaderM orderHeader;
    @DBRef
    private OrdItemDetailM ordItemDetailM;
    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;
}
