package com.aye.web.controller.order;

import com.aye.web.model.TokenResponse;
import com.aye.web.model.UserDto;
import com.aye.web.model.order.OrderHeaderMDto;
import com.aye.web.model.order.OrderStatusMDto;
import com.aye.web.service.OrderUiService;
import com.aye.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderUiController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private OrderUiService orderUiService;

    @GetMapping("/orderPage")
    public String getPostedOrder(HttpSession session, ModelMap model) {

        TokenResponse tokenResponse = tokenService.getTokens();

        if (tokenResponse != null) {
            session.setAttribute("accessToken", tokenResponse.getAccessToken());
            session.setAttribute("refreshToken", tokenResponse.getRefreshToken());
        }

        List<OrderHeaderMDto> allPostedOrder = orderUiService.getAllPostedOrder(tokenResponse.getAccessToken(), OrderStatusMDto.P);

        model.addAttribute("OrderHeaderDtos", allPostedOrder);

        return "Order/orderView";
    }

    @GetMapping("/orderDetails/{orderHeaderId}")
    public String orderById(@PathVariable("orderHeaderId") String orderHeaderId, HttpSession httpSession, ModelMap model){
        String accessToken = (String) httpSession.getAttribute("accessToken");
        OrderHeaderMDto orderHeaderMDto = orderUiService.findOrderById(orderHeaderId, accessToken);
        model.addAttribute("orderHeaderDto", orderHeaderMDto);
        return "Order/orderDetails";
    }

    @GetMapping("/approveOrder/{orderHeaderId}")
    public String approveOrder(@PathVariable("orderHeaderId") String orderHeaderId, HttpSession httpSession,
                               RedirectAttributes redirectAttributes,
                               ModelMap model){
        String accessToken = (String) httpSession.getAttribute("accessToken");
        String message = orderUiService.approveOrderHeader(orderHeaderId, accessToken);
        redirectAttributes.addAttribute("message", message);
        return "redirect:/orderDetails/"+orderHeaderId;
    }
}
