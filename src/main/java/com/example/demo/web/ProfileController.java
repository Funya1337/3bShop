package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProfileController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public String userProfile(Model model, Authentication authentication) {
        String username = authentication.getName();

        User user = userRepository.findByEmail(username);

        System.out.println("from db: " + user.getFirstName());
        System.out.println("from db: " + user.getLastName());

        // System.out.println(user.getLastName());

        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByEmail(username);

        // System.out.println(user.getFirstName());
        // System.out.println(user.getLastName());

        user.setFirstName(firstName);
        user.setLastName(lastName);

        userRepository.save(user);
        
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());

        // System.out.println(firstName);
        // System.out.println(lastName);
        return "redirect:/profile";
        // String firstName = request.getParameter("firstName");
        // String lastName = request.getParameter("lastName");
        // System.out.println(firstName + " " + lastName);
        // return "profile";
    }
    
}
