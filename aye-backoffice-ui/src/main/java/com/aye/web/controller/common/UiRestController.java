package com.aye.web.controller.common;

import com.aye.web.service.PaymentUiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Base64;

@RestController
public class UiRestController {

    @Autowired
    private PaymentUiService paymentUiService;

    @GetMapping("/payment/downloadAttachment/{id}")
    public ResponseEntity<String> downloadPaymentImage(@PathVariable("id") String id, HttpSession httpSession) throws Exception {
        String accessToken = (String) httpSession.getAttribute("accessToken");

        try {
            ResponseEntity<byte[]> response = paymentUiService.downloadPaymentAttachment(id, accessToken);
            String base64Image = Base64.getEncoder().encodeToString(response.getBody());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(response.getHeaders().getContentType());
            return new ResponseEntity<>(base64Image, httpHeaders, HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
