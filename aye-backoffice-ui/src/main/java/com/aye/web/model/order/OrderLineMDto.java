package com.aye.web.model.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderLineMDto {
    private String id;
    private String orderUomCode;
    private String itemCode;
    private BigDecimal orderUomQty;
    private BigDecimal orderDefaultUomQty;
    private BigDecimal convRate;
    private BigDecimal price;
    private BigDecimal amount;
    private String lineNotes;
    @JsonBackReference
    @ToString.Exclude
    private OrderHeaderMDto orderHeader;
    private OrdItemDetailMDto ordItemDetailM;

}
