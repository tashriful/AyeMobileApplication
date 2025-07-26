package com.aye.web.controller.user;

import com.aye.web.model.TokenRequest;
import com.aye.web.model.TokenResponse;
import com.aye.web.model.UserDto;
import com.aye.web.service.TokenService;
import com.aye.web.service.UserRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserRegistrationController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRegService userRegService;

    @Autowired
    private TokenService tokenService;


    @GetMapping("/userPage")
    public String getUserPage(HttpSession session, ModelMap model) {

        TokenResponse tokenResponse = tokenService.getTokens();

        if (tokenResponse != null) {
            session.setAttribute("accessToken", tokenResponse.getAccessToken());
            session.setAttribute("refreshToken", tokenResponse.getRefreshToken());
        }

        List<UserDto> userDtos = userRegService.getUsers(tokenResponse.getAccessToken());
//        UserDto userDto = userRegService.getUserById("656ec9c584540810aadc4c22", tokenResponse.getAccessToken());
        model.addAttribute("userDtos", userDtos);
        model.addAttribute("userDto", new UserDto());

     return "User/addUser";
    }

    @PostMapping("/userPage")
    String saveUser(UserDto userDto, BindingResult bindingResult, HttpSession httpSession, RedirectAttributes redirectAttributes, Model model) {
        if (userDto.getId() == null){
            if (userDto.getPassword() == null || userDto.getPassword().trim().isEmpty() ||
                    userDto.getUsername() == null || userDto.getUsername().trim().isEmpty()){
                model.addAttribute("message", "Not Successfull! , Input missing!");
                return "User/addUser";
            }
        }


        String accessToken = (String) httpSession.getAttribute("accessToken");
        UserDto saveUser = userRegService.saveUser(userDto, accessToken);
        if (saveUser != null) {
            redirectAttributes.addAttribute("message", "Operation successful!");
        }

        return "redirect:/userPage";
    }

    @GetMapping("/userPage/{id}")
    public String editUser(@PathVariable("id") String id, HttpSession httpSession, ModelMap model){
        String accessToken = (String) httpSession.getAttribute("accessToken");
        UserDto user = userRegService.getUserById(id, accessToken);
        model.addAttribute("userDto", user);
        return "User/addUser";
    }

}
