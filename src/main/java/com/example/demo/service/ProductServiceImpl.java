package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.web.dto.CreateProductDto;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(CreateProductDto createProductDto) {
        Product product = new Product(createProductDto.getName(), createProductDto.getPrice(), createProductDto.getRate(), createProductDto.getAmount());
        return productRepository.save(product);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Boolean deleteByName(String name) {
        Product product = productRepository.findByName(name);
        if (product != null) {
            productRepository.delete(product);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
