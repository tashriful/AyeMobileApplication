package com.aye.web.service;

import com.aye.web.model.order.OrderHeaderMDto;
import com.aye.web.model.order.OrderStatusMDto;

import java.util.List;

public interface OrderUiService {
    List<OrderHeaderMDto> getAllPostedOrder(String accessToken, OrderStatusMDto orderStatusMDto);

    OrderHeaderMDto findOrderById(String orderHeaderId, String accessToken);

    String approveOrderHeader(String orderHeaderId, String accessToken);
}
