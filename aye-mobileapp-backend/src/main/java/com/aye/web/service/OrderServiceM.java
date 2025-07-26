package com.aye.web.service;


import com.aye.web.exception.CustomExceptions;
import com.aye.web.exception.GeneralExceptions;
import com.aye.web.model.order.OrderHeaderM;
import com.aye.web.model.order.OrderHeaderMSearchDto;
import com.aye.web.model.order.OrderLineM;
import com.aye.web.model.order.OrderStatusM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderServiceM
{
    OrderHeaderM saveOrderHeader(OrderHeaderM orderHeader) throws GeneralExceptions;

    OrderHeaderM findByHeaderId(String headerId) throws CustomExceptions.ResourceNotFoundException;

    List<OrderHeaderM> findAllOrder();

    OrderHeaderM updateOrderHeader(OrderHeaderM orderHeader);

    List<OrderHeaderM> findOrderHeaderByStatus(OrderStatusM orderStatusM);

    OrderLineM saveOrderLine(OrderLineM orderLineM) throws GeneralExceptions;
    List<OrderLineM> findAllOrderLine();
    OrderLineM findOrderLineByID(String lineId) throws GeneralExceptions;

    List<OrderLineM> findOrderLinesByOrderHeader(String headerId);

    Page<OrderHeaderM> getAllOrder(OrderHeaderMSearchDto orderHeaderSearchDto, Pageable pageable);

    void deleteOrderLine(String headerId, String lineId) throws GeneralExceptions;

    void deleteOrderHeader(String headerId) throws GeneralExceptions;

    void updateOrderHeaderStatus(String headerId, String headerStatus) throws GeneralExceptions;

    void approveOrderHeader(String headerId) throws GeneralExceptions;
}
