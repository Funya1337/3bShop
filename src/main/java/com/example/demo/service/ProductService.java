package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.web.dto.CreateProductDto;

public interface ProductService {
    Product save(CreateProductDto createProductDto);
}
