//package com.aye.web.config;
//
//import com.aye.web.model.User;
//import com.aye.web.model.user.UserAccessTemltDtl;
//import com.aye.web.service.UserService;
//import com.aye.web.service.sys.RequestGroupHeaderService;
//import com.aye.web.service.sys.UserMenuService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//
//@Controller
//@SessionAttributes("manus")
//public class login {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserMenuService menuService;
//
//    @Autowired
//    private RequestGroupHeaderService requestGroupHeaderService;
//
////    private boolean isCurrentAuthenticationAnonymous() {
////        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        System.out.println(authentication.getName());
////        return authenticationTrustResolver.isAnonymous(authentication);
////    }
//
//    @GetMapping({"", "/", "/index"})
//    public String index(Map<String, Object> model) {
//        model.put("message", "Welcome to AYE World.");
//        return "index";
//    }
//
//    @GetMapping(value = "/login")
//    public String login(HttpServletRequest request,Model model, String error, String logout) {
////        if (isCurrentAuthenticationAnonymous()) {
//
//        if (error != null)
//            model.addAttribute("error", "Your username and password is invalid.");
//
//        if (logout != null)
//            model.addAttribute("message", "You have been logged out successfully.");
////            String referrer = request.getHeader("Referer");
////            request.getSession().setAttribute("url_prior_login", referrer);
//        return "login";
//
////        } else {
////            return "redirect:/index";
////        }
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        System.out.println(authentication.getName() + " PPP " + authentication.getCredentials());
////		System.out.println(isCurrentAuthenticationAnonymous()+" isCurrentAuthenticationAnonymous()");
////        if (isCurrentAuthenticationAnonymous()) {
////
////            if (error != null)
////                model.addAttribute("error", "Your username and password is invalid.");
////
////            if (logout != null)
////                model.addAttribute("message", "You have been logged out successfully.");
////            System.out.println("LLLl");
////            return "login";
////
////        } else {
////            return "main";
////        }
////        return "login";
//    }
//
//
////    @ModelAttribute("manus")
////    public List<SessionMenuHeader> setUpUserForm() {
////        List<SessionMenuHeader> userMenu = new ArrayList<>();
//////        setOrgAccess();
////        return userMenu;
////    }
//
//    @ModelAttribute("manus")
//    public List<UserAccessTemltDtl> setUpUserForm() {
//        List<UserAccessTemltDtl> userMenu = new ArrayList<>();
////        setOrgAccess();
//        return userMenu;
//    }
//
//
//    @ModelAttribute("reports")
//    public UserAccessTemltDtl setUpUserReport() {
//        UserAccessTemltDtl userRepoty = new UserAccessTemltDtl();
////        setOrgAccess();
//        return userRepoty;
//    }
//
//
//
//
////    @ModelAttribute("manusN")
////    public List<UserAccessTemltDtl> setUpUserForm1() {
////        List<UserAccessTemltDtl> userAccessTemltDtls = new ArrayList<>();
//////        setOrgAccess();
////        return userAccessTemltDtls;
////    }
//
//    @GetMapping("/main")
//    public String main(
//            @ModelAttribute("manus") List<UserAccessTemltDtl> userMenus,
////    @ModelAttribute("manusN") List<UserAccessTemltDtl> userAccessTemltDtls,
//            Model model) {
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User curUser = this.userService.findByName(auth.getName());
//        userMenus = this.menuService.getUserAccessNew1(curUser);
////        setReportMenu(new UserAccessTemltDtl(),1,model);
//
//        model.addAttribute("manus", userMenus);
//        return "main";
//    }
//
//    @GetMapping("/setRpt/{temltDtId}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public void setReportMenu(
//            @ModelAttribute("reports") UserAccessTemltDtl userReport,
//            @PathVariable("temltDtId") Integer temltDtId,
//            Model model
//    )
//    {
//        System.out.println("X");
////        this.requestGroupHeaderService.getUserReport(temltId);
////        userReport= this.requestGroupHeaderService.findById(reqGrpId);
//        userReport= this.menuService.getUserReport(temltDtId);
//        model.addAttribute("reports", userReport);
////        return null;
//    }
//}
