package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserRepository userRepository;
    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    public boolean isEmailAlreadyRegistered(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        if (isEmailAlreadyRegistered(registrationDto.getEmail())) {
            return "redirect:/registration?error";
        } else {
            userService.save(registrationDto);
            return "redirect:/registration?success";
        }
    }
}
