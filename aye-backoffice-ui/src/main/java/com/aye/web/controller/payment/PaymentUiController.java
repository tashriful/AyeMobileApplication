package com.aye.web.controller.payment;

import com.aye.web.model.TokenResponse;
import com.aye.web.model.order.OrderHeaderMDto;
import com.aye.web.model.order.OrderStatusMDto;
import com.aye.web.model.payment.PaymentStatusDto;
import com.aye.web.model.payment.PaymentsMDto;
import com.aye.web.service.OrderUiService;
import com.aye.web.service.PaymentUiService;
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
public class PaymentUiController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PaymentUiService paymentUiService;

    @GetMapping("/paymentPage")
    public String getPostedPayment(HttpSession session, ModelMap model) {

        TokenResponse tokenResponse = tokenService.getTokens();

        if (tokenResponse != null) {
            session.setAttribute("accessToken", tokenResponse.getAccessToken());
            session.setAttribute("refreshToken", tokenResponse.getRefreshToken());
        }

        List<PaymentsMDto> allPostedPayment = paymentUiService.getAllPostedPayment(tokenResponse.getAccessToken(), PaymentStatusDto.P);

        model.addAttribute("paymentDtos", allPostedPayment);

        return "Payment/paymentView";
    }

    @GetMapping("/paymentDetails/{paymentId}")
    public String paymentById(@PathVariable("paymentId") String paymentId, HttpSession httpSession, ModelMap model){
        String accessToken = (String) httpSession.getAttribute("accessToken");
        PaymentsMDto paymentsMDto = paymentUiService.findPaymentById(paymentId, accessToken);
        model.addAttribute("paymentsMDto", paymentsMDto);
        return "Payment/paymentDetails";
    }

    @GetMapping("/orderHeaderApproved/{paymentId}")
    public String approveOrder(@PathVariable("orderHeaderId") String orderHeaderId, HttpSession httpSession,
                               RedirectAttributes redirectAttributes,
                               ModelMap model){
        String accessToken = (String) httpSession.getAttribute("accessToken");
        String message = paymentUiService.approvePayment(orderHeaderId, accessToken);
        redirectAttributes.addAttribute("message", message);
        return "redirect:/orderDetails/"+orderHeaderId;
    }

}
