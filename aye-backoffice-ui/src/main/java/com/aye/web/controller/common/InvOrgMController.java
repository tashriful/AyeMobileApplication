package com.aye.web.controller.common;

import com.aye.web.model.InvOrgMDto;
import com.aye.web.service.InvOrgMService;
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
public class InvOrgMController {

    @Autowired
    private InvOrgMService invOrgMService;

    @GetMapping("/invOrgHierarchyPage")
    public String invOrgPage(HttpSession httpSession, ModelMap model) {
        String accessToken = (String) httpSession.getAttribute("accessToken");
        List<InvOrgMDto> invOrgMDtos = invOrgMService.getInvOrgs(accessToken);
        model.addAttribute("invOrgDto", new InvOrgMDto());
        model.addAttribute("invOrgDtos", invOrgMDtos);

        return "Common/invOrgHierarchyPage";
    }

    @PostMapping("/invOrgHierarchyPage")
    public String invOrgSave(InvOrgMDto invOrgMDto, RedirectAttributes redirectAttributes,
                                     HttpSession httpSession, BindingResult bindingResult, ModelMap model){
        String accessToken = (String) httpSession.getAttribute("accessToken");
//        if (bindingResult.hasErrors()) {
//            // If there are validation errors, return to the form page with error messages
//            model.addAttribute("message", "Not Successfull! , Input missing!");
//            return "invOrgHierarchyPage";
//        }

        InvOrgMDto savedInvOrg = invOrgMService.saveInvOrg(invOrgMDto, accessToken);
        if (savedInvOrg != null) {
            redirectAttributes.addAttribute("message", "Operation successful!");
        }

        return "redirect:/invOrgHierarchyPage";
    }

    @GetMapping("/invOrgHierarchyPage/{invOrgId}")
    public String invOrgPageById(@PathVariable("invOrgId") String invOrgId, HttpSession httpSession, ModelMap model) {
        String accessToken = (String) httpSession.getAttribute("accessToken");
        InvOrgMDto invOrgMDto = invOrgMService.getInvOrgById(invOrgId, accessToken);
        model.addAttribute("invOrgDto", invOrgMDto);
        return "Common/invOrgHierarchyPage";
    }

}
