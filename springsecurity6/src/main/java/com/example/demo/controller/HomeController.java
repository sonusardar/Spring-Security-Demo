package com.example.demo.controller;


import com.example.demo.config.UserDetailsServiceImpl;
import com.example.demo.entity.UserDtls;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
@Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/")
    public String index(Principal p) {
        System.out.println(p);
        return "index";
    }

    @GetMapping("/signin")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginHandle(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request){
       UserDetails principal = this.userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(principal,principal.getPassword(),principal.getAuthorities());

        System.out.println(authReq);


        SecurityContext context = SecurityContextHolder.createEmptyContext();
        System.out.println(context);
        context.setAuthentication(authReq);

//        authReq.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));



        System.out.println(username);
        System.out.println(password);
        return ResponseEntity.ok("ok");
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
        }

        else {
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
