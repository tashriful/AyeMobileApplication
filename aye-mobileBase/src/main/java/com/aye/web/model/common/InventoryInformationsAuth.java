package com.aye.web.model.common;

import com.aye.web.model.order.OrderTrnsTypeM;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class InventoryInformationsAuth {

    private String id;

    private String code;

    private String name;

    private String address;

    private Long parentAppInvInfoMId;

    private List<OrderTrnsTypeM> orderTrnsTypeList;

}
