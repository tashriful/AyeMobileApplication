package com.aye.web.controller.user;

import com.aye.web.model.AccessTemplateDto;
import com.aye.web.service.AccessTemplateService;
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
public class AccessTemplateController {

    @Autowired
    private AccessTemplateService accessTemplateService;

    @GetMapping("/accessTemplatePage")
    public String accessTemplatePage(HttpSession httpSession, ModelMap model) {
        String accessToken = (String) httpSession.getAttribute("accessToken");
        List<AccessTemplateDto> accessTemplates = accessTemplateService.getAccessTemplates(accessToken);
        model.addAttribute("accessTemplateDto", new AccessTemplateDto());
        model.addAttribute("accessTemplateDtos", accessTemplates);

        return "User/accessTemplateSave";
    }

    @PostMapping("/accessTemplatePage")
    public String accessTemplateSave(@Valid AccessTemplateDto accessTemplateDto, RedirectAttributes redirectAttributes,
                                     HttpSession httpSession, BindingResult bindingResult, ModelMap model) {
        String accessToken = (String) httpSession.getAttribute("accessToken");
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the form page with error messages
            model.addAttribute("message", "Not Successfull! , Input missing!");
            return "User/accessTemplateSave";
        }

        AccessTemplateDto savedAccessTemplate = accessTemplateService.saveAccessTemplate(accessTemplateDto, accessToken);
        if (savedAccessTemplate != null) {
            redirectAttributes.addAttribute("message", "Operation successful!");
        }

        return "redirect:/accessTemplatePage";
    }

    @GetMapping("/accessTemplatePage/{id}")
    public String getAccessTemplateById(@PathVariable("id") String id, HttpSession httpSession, ModelMap model) {
        String accessToken = (String) httpSession.getAttribute("accessToken");
        AccessTemplateDto accessTemplate = accessTemplateService.getAccessTemplateById(id, accessToken);
        model.addAttribute("accessTemplateDto", accessTemplate);

        return "User/accessTemplateSave";
    }

}
