package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class adminPanelController {
    @GetMapping("/adminpanel")
    public String index(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "adminPanel";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
    }
}
