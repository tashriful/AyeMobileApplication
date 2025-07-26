package com.aye.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    public String home(HttpSession session){
        Object httpSession = session.getAttribute("accessToken");
        if (httpSession == null) {
            // Redirect the user to the login page if the token is not in the session
            return "redirect:/login";
        }

        return "main";
    }

}
