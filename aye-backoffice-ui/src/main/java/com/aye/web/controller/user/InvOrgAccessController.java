package com.aye.web.controller.user;

import com.aye.web.model.*;
import com.aye.web.service.*;
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
public class InvOrgAccessController {

    @Autowired
    private InvOrgMService invOrgMService;

    @Autowired
    private AccessTemplateDetailsService accessTemplateDetailsService;

    @Autowired
    private UserInvOrgAccessService userInvOrgAccessService;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Register a custom property editor to convert empty strings to null
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/invOrgAccessPage/{tmpltId}/{tmpltDtlId}/{orgId}")
    public String invOrgAccessPage(HttpSession httpSession, ModelMap model, @PathVariable("tmpltId") String tmpltId, @PathVariable("tmpltDtlId") String tmpltDtlId,
                                   @PathVariable("orgId") String orgId) {
        String accessToken = (String) httpSession.getAttribute("accessToken");
        UserInvOrgAccessDto userInvOrgAccessDto = new UserInvOrgAccessDto();

        List<InvOrgMDto> invOrgs = invOrgMService.getInvOrgsByOrg(orgId, accessToken);
        AccessTemplateDetailsMDto accessTemplateDetailsById = accessTemplateDetailsService.getAccessTemplateDetailsById(tmpltDtlId, accessToken);

        userInvOrgAccessDto.setUserAccessTemplateDetails(accessTemplateDetailsById);

        List<UserInvOrgAccessDto> userInvOrgAccessDtos = userInvOrgAccessService.getAllInvOrgAccessByTmpltDtl(tmpltDtlId, accessToken);

        model.addAttribute("invOrgs", invOrgs);
        model.addAttribute("userInvOrgAccessDto", userInvOrgAccessDto);
        model.addAttribute("userInvOrgAccessDtos", userInvOrgAccessDtos);
        model.addAttribute("tmpltDtlId", tmpltDtlId);
        model.addAttribute("tmpltId", tmpltId);
        model.addAttribute("orgId", orgId);

        return "User/userInvOrgAccessSave";
    }

    @PostMapping("/invOrgAccessPage/{tmpltId}/{tmpltDtlId}/{orgId}")
    public String invOrgAccessSave(@PathVariable("tmpltId") String tmpltId,
                                   @PathVariable("tmpltDtlId") String tmpltDtlId,
                                   @PathVariable("orgId") String orgId,
                                   @Valid UserInvOrgAccessDto userInvOrgAccessDto, RedirectAttributes redirectAttributes,
                                   HttpSession httpSession, BindingResult bindingResult, ModelMap model) {
        String accessToken = (String) httpSession.getAttribute("accessToken");
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the form page with error messages
            model.addAttribute("message", "Not Successfull! , Input missing!");
            return "User/userInvOrgAccessSave";
        }

        UserInvOrgAccessDto invOrgAccessDto = userInvOrgAccessService.saveUserInvOrgAccess(userInvOrgAccessDto, accessToken);

        if (invOrgAccessDto != null) {
            redirectAttributes.addAttribute("message", "Operation successful!");
        }

        return "redirect:/invOrgAccessPage/"+ tmpltId + '/' + tmpltDtlId + '/' + orgId;
    }
//
//    @GetMapping("/menuDetailsPage/{menuId}/{menuDtlId}")
//    public String getMenuDetailsById(@PathVariable("menuId") String menuId,@PathVariable("menuDtlId") String menuDtlId, HttpSession httpSession, ModelMap model){
//        String accessToken = (String) httpSession.getAttribute("accessToken");
//        UserMenuDetailsDto userMenuDetail = userMenuDetailsService.findUserMenuDetailsById(menuDtlId, accessToken);
//        UserMenuDto userMenuById = userMenuService.findUserMenuById(menuId, accessToken);
//        userMenuDetail.setUserMenuM(userMenuById);
//        model.addAttribute("userMenuDetailsDto", userMenuDetail);
//        model.addAttribute("userMenuId", userMenuById.getId());
//
//        return "User/userMenuDetailsSave";
//    }

}
