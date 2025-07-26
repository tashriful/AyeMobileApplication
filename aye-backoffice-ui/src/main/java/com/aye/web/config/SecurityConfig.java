//package com.aye.web.config;
//
//import com.aye.web.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created by Toufiq on 11/6/2019.
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig
//        extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    private UserService userService;
//
//
//    @Autowired
//    private HttpServletRequest request;
//
//
//    @Autowired
//    private RefererAuthenticationSuccessHandler refererAuthenticationSuccessHandler;
//
//
//    private static final String[] IGNORED_RESOURCE_LIST = new String[]{
//            "/webjars/**", "/css/**", "/js/**", "/images/**"
//    };
//
//
//    private static final String[] PERMITALL_RESOURCE_LIST = new String[]{
//            "/", "/registration", "/get_any_image/**", "/help", "/about", "/login", "/logout"};
//
//    //    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//
//        return super.authenticationManagerBean();
//
//    }
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        PasswordEncoder encoder = new BCryptPasswordEncoder(); // or any other compatible encoder
////        return encoder;
////    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userService);
////        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//
//    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.
//                authorizeRequests()
//                .antMatchers(PERMITALL_RESOURCE_LIST).permitAll();
//
//
//        http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/login").permitAll()
//                .antMatchers("/registration").permitAll().antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
//                .authenticated().and().csrf().disable().formLogin().loginPage("/login").successHandler(successHandler())//defaultSuccessUrl("/main", true) //
//                .failureUrl("/login?error=true")
//                .usernameParameter("username").passwordParameter("password").and().logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").and()
//                .exceptionHandling().accessDeniedPage("/access-denied");
//
//
//    }
//
////	@Override
////	public void configure(WebSecurity web) throws Exception {
////		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
////	}
//
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                //Spring Security ignores request to static resources such as CSS or JS files.
//                .ignoring()
//                .antMatchers(IGNORED_RESOURCE_LIST);
//    }
//
////    @Bean
////    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
////        return new AuthenticationTrustResolverImpl();
////    }
//
//
//    @Bean
//    public SavedRequestAwareAuthenticationSuccessHandler
//    savedRequestAwareAuthenticationSuccessHandler() {
//
//        SavedRequestAwareAuthenticationSuccessHandler auth
//                = new SavedRequestAwareAuthenticationSuccessHandler();
//        auth.setTargetUrlParameter("targetUrl");
////        this.refererAuthenticationSuccessHandler.setUpUserForm1();
//        return auth;
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler successHandler() {
//        return new RefererAuthenticationSuccessHandler();
//    }
//
//
//}
