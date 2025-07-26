package com.aye.web.controller;

import com.aye.web.utill.ResponseUtils;
import com.aye.web.exception.GeneralExceptions;
import com.aye.web.service.OrderServiceM;
import com.aye.web.model.order.OrderHeaderM;
import com.aye.web.model.order.OrderHeaderMSearchDto;
import com.aye.web.model.order.OrderLineM;
import com.aye.web.model.order.OrderStatusM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ord")
public class OrderMController {

    @Autowired
    private OrderServiceM orderService;

    @GetMapping("/orderHeaders/{headerId}")
    public ResponseEntity<?> getOrderHeader(@PathVariable("headerId") String headerId) {
        OrderHeaderM orderHeaderM = this.orderService.findByHeaderId(headerId);
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Order retrieved successfully", orderHeaderM));

    }

    @PostMapping("/orderHeaders")
    public ResponseEntity<?> saveOrderHeader(@RequestBody OrderHeaderM orderHeaderM) throws GeneralExceptions {
        OrderHeaderM orderHeaderM1 = null;
        orderHeaderM1 = orderService.saveOrderHeader(orderHeaderM);
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Order saved successfully", orderHeaderM1));

    }

    @DeleteMapping("/orderHeaders/{headerId}")
    public ResponseEntity<?> deleteOrderHeader(@PathVariable("headerId") String headerId) throws GeneralExceptions {
        orderService.deleteOrderHeader(headerId);
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Order deleted successfully", null));
    }

    @GetMapping("/orderHeaders/{headerId}/status/{headerStatus}")
    public ResponseEntity<?> updateOrderHeaderStatus(@PathVariable("headerId") String headerId,
                                                     @PathVariable("headerStatus") String headerStatus) {

        orderService.updateOrderHeaderStatus(headerId, headerStatus);
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Order status updated successfully", null));
    }

    @GetMapping("/getOrderHeaders/status/{orderStatus}")
    public ResponseEntity<?> getAllOrderByStatus(@PathVariable("orderStatus") OrderStatusM orderStatusM) {
        List<OrderHeaderM> allOrder = this.orderService.findOrderHeaderByStatus(orderStatusM);
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Order retrieved successfully", allOrder));
    }


    @PostMapping("/orderLines")
    public ResponseEntity<?> saveOrderLine(@RequestBody OrderLineM orderLineM) {
        OrderLineM saveOrderLine = orderService.saveOrderLine(orderLineM);
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Order Line saved successfully", saveOrderLine));
    }

    @GetMapping("/orderLines")
    public ResponseEntity<?> findAllOrderLine() {
        List<OrderLineM> allOrderLine = orderService.findAllOrderLine();
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Order Lines retrieved successfully", allOrderLine));
    }

    @GetMapping("/orderLines/{lineId}")
    public ResponseEntity<?> getOneOrderLine(@PathVariable("lineId") String lineId) {
        OrderLineM orderLineByID = this.orderService.findOrderLineByID(lineId);
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Order Line retrieved successfully", orderLineByID));

    }

    @GetMapping("/orderLines/orderHeader/{headerId}")
    public ResponseEntity<?> getOrderLinesByOrderHeader(@PathVariable("headerId") String headerId) {
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Order Lines retrieved successfully", this.orderService.findOrderLinesByOrderHeader(headerId)));
    }

    @DeleteMapping("/deleteOrderLine/{headerId}/{lineId}")
    public ResponseEntity<?> deleteOrderLine(@PathVariable("headerId") String headerId, @PathVariable("lineId") String lineId) {
        orderService.deleteOrderLine(headerId, lineId);

        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Order Line deleted successfully", null));
    }

    @GetMapping("/searchOrderHeader")
    public ResponseEntity<?> getAllOrderHeader(@RequestBody OrderHeaderMSearchDto orderHeaderSearchDto,
                                                                @RequestParam(required = false) Integer page,
                                                                @RequestParam(required = false) Integer size) {


        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 10);

        Page<OrderHeaderM> orderHeaderMPage = orderService.getAllOrder(orderHeaderSearchDto, pageable);
        return ResponseEntity.ok(ResponseUtils.success(HttpStatus.OK.value(), "Order Headers retrieved successfully", orderHeaderMPage));
    }


}
