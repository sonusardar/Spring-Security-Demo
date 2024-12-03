package com.example.demo.controller;

import com.example.demo.entity.UserDtls;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @ModelAttribute
    private void userDetails(Model m, Principal p) {
        String email = p.getName();
        UserDtls user = userRepo.findByEmail(email);

        m.addAttribute("user", user);

    }

    @GetMapping ("/user")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")

    public String AdminHome(){
        System.out.println("admin controller call");
        return "admin/index";
    }

}
