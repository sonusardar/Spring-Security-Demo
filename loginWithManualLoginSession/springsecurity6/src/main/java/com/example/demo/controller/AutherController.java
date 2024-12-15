package com.example.demo.controller;

import com.example.demo.dto.BlogDto;
import com.example.demo.entity.Blogs;
import com.example.demo.entity.UserDtls;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class AutherController {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/admin/blog/list")
    public String BlogList(Principal principal, Model m){
        if (principal != null){
            UserDtls userDtls = userRepository.findByEmail(principal.getName());
            List<Blogs> blogs = this.blogRepository.findByAuther(userDtls);
            System.out.println(blogs);
            m.addAttribute("blog",blogs);


        }
        return "admin/list";
    }

    @RequestMapping("/admin/blog/new")
    public String BlogNew(Principal principal){
        System.out.println(principal);
        if (principal != null){
            System.out.println(principal.toString());
            System.out.println(principal.getName());
            UserDtls userDtls = userRepository.findByEmail(principal.getName());
            System.out.println(userDtls);
        }
        return "admin/new";
    }

    @RequestMapping(value = "/admin/blog/new",method = RequestMethod.POST)
    public ResponseEntity<?> createBlog(@ModelAttribute BlogDto blogDto,Principal principal){
        if (principal != null) {
            UserDtls userDtls = userRepository.findByEmail(principal.getName());

            Blogs blog = new Blogs();
            blog.setDescription(blogDto.getDescription());
            blog.setTitle(blogDto.getTitle());
            blog.setAuther(userDtls);


            blogRepository.save(blog);

            System.out.println(blogDto.toString());
        }
        System.out.println(principal);
        return ResponseEntity.ok("ok");
    }

}
