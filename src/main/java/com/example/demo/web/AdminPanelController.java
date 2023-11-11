package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.web.dto.CreateProductDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/adminpanel")
public class AdminPanelController {

    @Autowired
    private ProductRepository productRepository;
    private ProductService productService;

    public AdminPanelController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("product")
    public CreateProductDto createProductDto() {
        return new CreateProductDto();
    }

    @GetMapping
    public String showAdminPanelContent(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "adminPanel";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
    }

    @PostMapping
    public String processAddProductForm(@ModelAttribute("product") CreateProductDto createProductDto) {
        productService.save(createProductDto);
        System.out.println(createProductDto.getName());
        System.out.println(createProductDto.getPrice());
        System.out.println(createProductDto.getRate());
        System.out.println(createProductDto.getAmount());
        return "redirect:/adminpanel";
    }
}
