package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Product;
import com.example.demo.model.User;
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

    public boolean isProductAlreadyCreated(String name) {
        Product product = productRepository.findByName(name);
        return product != null;
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
        if (isProductAlreadyCreated(createProductDto.getName())) {
            return "redirect:/adminpanel?error";
        } else {
            productService.save(createProductDto);
            return "redirect:/adminpanel?success";
        }
    }
    
    @PostMapping("/product/find")
    public String processFindProductForm(@ModelAttribute("product") CreateProductDto createProductDto) {
        String name = createProductDto.getName();
        Product productInfo = productService.findByName(name);
        if (productInfo != null) {
            System.out.println(productInfo.getName());
            System.out.println(productInfo.getPrice());
            System.out.println(productInfo.getRate());
            System.out.println(productInfo.getAmount());
            return "redirect:/adminpanel?success";
        } else {
            System.out.println("Can't find product!");
            return "redirect:/adminpanel?error";
        }
    }
    
    @PostMapping("/product/delete")
    public String processDeleteProductForm(@ModelAttribute("product") CreateProductDto createProductDto) {
        String name = createProductDto.getName();
        if (productService.deleteByName(name)) {
            System.out.println("Successfully delete product!");
            return "redirect:/adminpanel?success";
        } else {
            System.out.println("Can't find product name!");
            return "redirect:/adminpanel?error";
        }

    }
}
