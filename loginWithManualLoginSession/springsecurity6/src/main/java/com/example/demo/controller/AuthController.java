package com.example.demo.controller;


import com.example.demo.config.UserDetailsServiceImpl;
import com.example.demo.entity.UserDtls;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;


    private SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();



    @GetMapping("/signin")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginHandle(@RequestParam("username") String username,
                                         @RequestParam("password") String password,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {

        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
                username, password
        );

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContext context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);
        securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context,request,response);

        System.out.println(token.toString());
        System.out.println(authentication);

//
//       UserDetails principal = this.userDetailsService.loadUserByUsername(username);
//        UsernamePasswordAuthenticationToken authReq
//                = new UsernamePasswordAuthenticationToken(principal,principal.getPassword(),principal.getAuthorities());
//
//        System.out.println(authReq);
//
//
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        System.out.println(context);
//        context.setAuthentication(authReq);
//
////        authReq.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//
//
//        System.out.println(username);
//        System.out.println(password);
        return "redirect:/admin";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/createUser")
    public String createuser(@ModelAttribute UserDtls user, HttpSession session) {

        // System.out.println(user);

        boolean f = userService.checkEmail(user.getEmail());

        if (f) {
            session.setAttribute("msg", "Email Id alreday exists");
        } else {
            UserDtls userDtls = userService.createUser(user);
            if (userDtls != null) {
                session.setAttribute("msg", "Register Sucessfully");
            } else {
                session.setAttribute("msg", "Something wrong on server");
            }
        }

        return "redirect:/register";
    }

}
