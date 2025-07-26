package com.aye.web.controller.user;

import com.aye.web.model.UserMenuDto;
import com.aye.web.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserMenuController {

    @Autowired
    private UserMenuService userMenuService;

    @GetMapping("/menuPage")
    public String menuPage(HttpSession httpSession, ModelMap model) {
        String accessToken = (String) httpSession.getAttribute("accessToken");
        List<UserMenuDto> allUserMenu = userMenuService.findAllUserMenu(accessToken);
        model.addAttribute("userMenuDto", new UserMenuDto());
        model.addAttribute("userMenuDtos", allUserMenu);

        return "User/userMenuSave";
    }

    @PostMapping("/menuPage")
    public String menuSave(@Valid UserMenuDto userMenuDto, RedirectAttributes redirectAttributes,
                                     HttpSession httpSession, BindingResult bindingResult, ModelMap model){
        String accessToken = (String) httpSession.getAttribute("accessToken");
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the form page with error messages
            model.addAttribute("message", "Not Successfull! , Input missing!");
            return "User/userMenuSave";
        }

        UserMenuDto userMenu = userMenuService.saveUserMenu(userMenuDto, accessToken);
        if (userMenu != null) {
            redirectAttributes.addAttribute("message", "Operation successful!");
        }

        return "redirect:/menuPage";
    }

    @GetMapping("/menuPage/{id}")
    public String getMenuById(@PathVariable("id") String id, HttpSession httpSession, ModelMap model){
        String accessToken = (String) httpSession.getAttribute("accessToken");
        UserMenuDto userMenu = userMenuService.findUserMenuById(id, accessToken);
        model.addAttribute("userMenuDto", userMenu);

        return "User/userMenuSave";
    }

}
