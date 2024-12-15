package com.example.demo.controller;

import com.example.demo.entity.UserDtls;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BlogRepository blogRepository;

    @ModelAttribute
    private void userDetails(Model m, Principal p) {
        if (p != null){
            String email = p.getName();
            UserDtls user = userRepo.findByEmail(email);
            m.addAttribute("user", user);

        }



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

    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    @GetMapping("/")
    public String index(Model m) {
        System.out.println(passwordEncode.encode("admin"));

        m.addAttribute("blogs",this.blogRepository.findAll());


        return "index";
    }

}
