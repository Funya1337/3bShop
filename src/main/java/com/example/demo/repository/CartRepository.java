package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cart;
import java.util.List;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    
}
