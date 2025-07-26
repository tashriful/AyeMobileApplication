package com.aye.web.controller.common;

import com.aye.web.model.OrgHierarchyMDto;
import com.aye.web.service.OrgHierarchyMService;
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
public class OrgHierarchyMController {

    @Autowired
    private OrgHierarchyMService orgHierarchyMService;

    @GetMapping("/orgHierarchyPage")
    public String orgPage(HttpSession httpSession, ModelMap model) {
        String accessToken = (String) httpSession.getAttribute("accessToken");
        List<OrgHierarchyMDto> orgHierarchyMDtos = orgHierarchyMService.getOrgs(accessToken);
        model.addAttribute("organizationDto", new OrgHierarchyMDto());
        model.addAttribute("organizationDtos", orgHierarchyMDtos);

        return "Common/OrganizationSave";
    }

    @PostMapping("/orgHierarchyPage")
    public String orgSave(@Valid OrgHierarchyMDto orgHierarchyMDto, RedirectAttributes redirectAttributes,
                                     HttpSession httpSession, BindingResult bindingResult, ModelMap model){
        String accessToken = (String) httpSession.getAttribute("accessToken");
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the form page with error messages
            model.addAttribute("message", "Not Successfull! , Input missing!");
            return "Common/OrganizationSave";
        }

        OrgHierarchyMDto savedOrg = orgHierarchyMService.saveOrg(orgHierarchyMDto, accessToken);
        if (savedOrg != null) {
            redirectAttributes.addAttribute("message", "Operation successful!");
        }

        return "redirect:/orgHierarchyPage";
    }

    @GetMapping("/orgHierarchyPage/{orgId}")
    public String orgPageById(@PathVariable("orgId") String orgId, HttpSession httpSession, ModelMap model) {
        String accessToken = (String) httpSession.getAttribute("accessToken");
        OrgHierarchyMDto orgHierarchyMDto = orgHierarchyMService.getOrgById(orgId, accessToken);
        model.addAttribute("organizationDto", orgHierarchyMDto);
        return "Common/OrganizationSave";
    }

}
