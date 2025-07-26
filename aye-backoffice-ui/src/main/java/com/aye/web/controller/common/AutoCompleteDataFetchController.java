package com.aye.web.controller.common;

import com.aye.web.model.AccessTemplateDto;
import com.aye.web.model.OrgHierarchyMDto;
import com.aye.web.model.UserMenuDto;
import com.aye.web.service.AccessTemplateService;
import com.aye.web.service.OrgHierarchyMService;
import com.aye.web.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class AutoCompleteDataFetchController {

    @Autowired
    private OrgHierarchyMService orgHierarchyMService;

    @Autowired
    private UserMenuService userMenuService;

    @Autowired
    private AccessTemplateService accessTemplateService;

    @GetMapping("/orgHierarchyData")
    public ResponseEntity<?> orgData(HttpSession httpSession) {
        String accessToken = (String) httpSession.getAttribute("accessToken");
        List<OrgHierarchyMDto> orgHierarchyMDtos = orgHierarchyMService.getOrgs(accessToken);

        return ResponseEntity.ok(orgHierarchyMDtos);
    }

    @GetMapping("/menuData")
    public ResponseEntity<?> menuData(HttpSession httpSession) {
        String accessToken = (String) httpSession.getAttribute("accessToken");
        List<UserMenuDto> menuDtos = userMenuService.findAllUserMenu(accessToken);

        return ResponseEntity.ok(menuDtos);
    }

    @GetMapping("/templateData")
    public ResponseEntity<?> templateData(HttpSession httpSession) {
        String accessToken = (String) httpSession.getAttribute("accessToken");
        List<AccessTemplateDto> accessTemplates = accessTemplateService.getAccessTemplates(accessToken);

        return ResponseEntity.ok(accessTemplates);
    }
}
