package com.aye.web.controller.user;

import com.aye.web.model.UserAccessMDto;
import com.aye.web.model.UserMenuDetailsDto;
import com.aye.web.model.UserMenuDto;
import com.aye.web.service.UserAccessService;
import com.aye.web.service.UserMenuDetailsService;
import com.aye.web.service.UserMenuService;
import com.aye.web.service.UserRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserAccessController {

    @Autowired
    private UserAccessService userAccessService;

    @Autowired
    private UserRegService userRegService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Register a custom property editor to convert empty strings to null
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/userAccessPage/{userId}")
    public String userAccessPage(HttpSession httpSession, ModelMap model, @PathVariable("userId") String userId) {
        String accessToken = (String) httpSession.getAttribute("accessToken");
        List<UserAccessMDto> userAccessByUser = userAccessService.getUserAccessByUser(accessToken, userId);
        UserAccessMDto userAccessMDto = new UserAccessMDto();

        userAccessMDto.setUser(userRegService.getUserById(userId, accessToken));
//        userMenuDetailsDto.setIsActive(true);
        model.addAttribute("userAccessDto", userAccessMDto);
        model.addAttribute("userAccessDtos", userAccessByUser);
        model.addAttribute("userId", userId);

        return "User/userAccessSave";
    }

    @PostMapping("/userAccessPage/{userId}")
    public String userAccessSave( @PathVariable("userId") String userId,@Valid UserAccessMDto userAccessMDto, RedirectAttributes redirectAttributes,
                                     HttpSession httpSession, BindingResult bindingResult, ModelMap model){
        String accessToken = (String) httpSession.getAttribute("accessToken");
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the form page with error messages
            model.addAttribute("message", "Not Successfull! , Input missing!");
            return "User/userAccessSave";
        }

        UserAccessMDto savedUserAccess = userAccessService.saveUserAccess(userAccessMDto, accessToken);
        if (savedUserAccess != null) {
            redirectAttributes.addAttribute("message", "Operation successful!");
        }

        return "redirect:/userAccessPage/"+userId;
    }

    @GetMapping("/userAccessPage/{userId}/{accessId}")
    public String getAccessById(@PathVariable("userId") String userId,@PathVariable("accessId") String accessId, HttpSession httpSession, ModelMap model){
        String accessToken = (String) httpSession.getAttribute("accessToken");
        UserAccessMDto userAccessById = userAccessService.findUserAccessById(accessId, accessToken);
        userAccessById.setUser(userRegService.getUserById(userId, accessToken));
        model.addAttribute("userAccessDto", userAccessById);

        return "User/userAccessSave";
    }

}
