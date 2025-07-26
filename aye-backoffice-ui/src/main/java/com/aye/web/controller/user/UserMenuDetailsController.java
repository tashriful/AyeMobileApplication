package com.aye.web.controller.user;

import com.aye.web.model.UserMenuDetailsDto;
import com.aye.web.model.UserMenuDto;
import com.aye.web.service.UserMenuDetailsService;
import com.aye.web.service.UserMenuService;
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
public class UserMenuDetailsController {

    @Autowired
    private UserMenuDetailsService userMenuDetailsService;

    @Autowired
    private UserMenuService userMenuService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Register a custom property editor to convert empty strings to null
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/menuDetailsPage/{menuId}")
    public String menuDetailsPage(HttpSession httpSession, ModelMap model, @PathVariable("menuId") String menuId) {
        String accessToken = (String) httpSession.getAttribute("accessToken");
        List<UserMenuDetailsDto> userMenuDetails = userMenuDetailsService.getAllUserMenuDetailsByMenu(accessToken, menuId);
        UserMenuDetailsDto userMenuDetail = new UserMenuDetailsDto();
        UserMenuDto userMenu = userMenuService.findUserMenuById(menuId, accessToken);
        userMenuDetail.setUserMenuM(userMenu);
//        userMenuDetailsDto.setIsActive(true);
        model.addAttribute("userMenuDetailsDto", userMenuDetail);
        model.addAttribute("userMenuDetailsDtos", userMenuDetails);
        model.addAttribute("userMenuId", userMenu.getId());

        return "User/userMenuDetailsSave";
    }

    @PostMapping("/menuDetailsPage/{menuId}")
    public String menuDetailsSave( @PathVariable("menuId") String menuId,@Valid UserMenuDetailsDto userMenuDetailsDto, RedirectAttributes redirectAttributes,
                                     HttpSession httpSession, BindingResult bindingResult, ModelMap model){
        String accessToken = (String) httpSession.getAttribute("accessToken");
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the form page with error messages
            model.addAttribute("message", "Not Successfull! , Input missing!");
            return "User/userMenuDetailsSave";
        }

        UserMenuDetailsDto menuDetailsDto = userMenuDetailsService.saveUserMenuDetails(userMenuDetailsDto, accessToken);
        if (menuDetailsDto != null) {
            redirectAttributes.addAttribute("message", "Operation successful!");
        }

        return "redirect:/menuDetailsPage/"+menuId;
    }

    @GetMapping("/menuDetailsPage/{menuId}/{menuDtlId}")
    public String getMenuDetailsById(@PathVariable("menuId") String menuId,@PathVariable("menuDtlId") String menuDtlId, HttpSession httpSession, ModelMap model){
        String accessToken = (String) httpSession.getAttribute("accessToken");
        UserMenuDetailsDto userMenuDetail = userMenuDetailsService.findUserMenuDetailsById(menuDtlId, accessToken);
        UserMenuDto userMenuById = userMenuService.findUserMenuById(menuId, accessToken);
        userMenuDetail.setUserMenuM(userMenuById);
        model.addAttribute("userMenuDetailsDto", userMenuDetail);
        model.addAttribute("userMenuId", userMenuById.getId());

        return "User/userMenuDetailsSave";
    }

}
