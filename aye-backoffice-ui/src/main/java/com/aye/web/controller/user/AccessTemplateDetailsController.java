package com.aye.web.controller.user;

import com.aye.web.model.AccessTemplateDetailsMDto;
import com.aye.web.model.AccessTemplateDto;
import com.aye.web.service.AccessTemplateDetailsService;
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
public class AccessTemplateDetailsController {

    @Autowired
    private AccessTemplateDetailsService accessTemplateDetailsService;

    @Autowired
    private AccessTemplateService accessTemplateService;

    @GetMapping("/accessTemplateDetailsPage/{templateId}")
    public String accessTemplateDetailsPage(@PathVariable("templateId") String templateId, HttpSession httpSession, ModelMap model) {
        String accessToken = (String) httpSession.getAttribute("accessToken");
        List<AccessTemplateDetailsMDto> accessTemplatesDetails = accessTemplateDetailsService.getAccessTemplatesDetailsByTemplate(templateId, accessToken);
        AccessTemplateDetailsMDto accessTemplateDetail =  new AccessTemplateDetailsMDto();
        AccessTemplateDto accessTemplateById = accessTemplateService.getAccessTemplateById(templateId, accessToken);


        accessTemplateDetail.setUserAccessTemplateM(accessTemplateById);

        model.addAttribute("accessTemplateDetailsDto",accessTemplateDetail);
        model.addAttribute("accessTemplateDetailsDtos", accessTemplatesDetails);
        model.addAttribute("templateId", templateId);

        return "User/accessTemplateDetailsSave";
    }

    @PostMapping("/accessTemplateDetailsPage/{templateId}")
    public String accessTemplateDetailsSave(@PathVariable("templateId") String templateId, @Valid AccessTemplateDetailsMDto accessTemplateDetailsMDto, RedirectAttributes redirectAttributes,
                                     HttpSession httpSession, BindingResult bindingResult, ModelMap model){
        String accessToken = (String) httpSession.getAttribute("accessToken");
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the form page with error messages
            model.addAttribute("message", "Not Successfull! , Input missing!");
            return "User/accessTemplateDetailsSave";
        }

        AccessTemplateDetailsMDto accessTemplateDetails = accessTemplateDetailsService.saveAccessTemplateDetails(accessTemplateDetailsMDto, accessToken);
        if (accessTemplateDetails != null) {
            redirectAttributes.addAttribute("message", "Operation successful!");
        }
        model.addAttribute("templateId", templateId);

        return "redirect:/accessTemplateDetailsPage/"+templateId;
    }

    @GetMapping("/accessTemplateDetailsPage/{tmpltId}/{tmpltDltId}")
    public String getAccessTemplateDetailsById(@PathVariable("tmpltId") String tmpltId, @PathVariable("tmpltDltId") String tmpltDltId,  HttpSession httpSession, ModelMap model){
        String accessToken = (String) httpSession.getAttribute("accessToken");
        AccessTemplateDetailsMDto templateDetailsById = accessTemplateDetailsService.getAccessTemplateDetailsById(tmpltDltId, accessToken);
        templateDetailsById.setUserAccessTemplateM(accessTemplateService.getAccessTemplateById(tmpltId, accessToken));
        model.addAttribute("accessTemplateDetailsDto", templateDetailsById);
        model.addAttribute("templateId", tmpltId);

        return "User/accessTemplateDetailsSave";
    }

}
