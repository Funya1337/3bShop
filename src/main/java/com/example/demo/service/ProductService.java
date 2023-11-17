package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.web.dto.CreateProductDto;

public interface ProductService {
    Product save(CreateProductDto createProductDto);
    Product findByName(String name);
    Boolean deleteByName(String name);
    List<Product> findAll();
}
