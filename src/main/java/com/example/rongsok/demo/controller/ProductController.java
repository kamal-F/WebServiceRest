package com.example.rongsok.demo.controller;


import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rongsok.demo.model.Product;
import com.example.rongsok.demo.model.ProductResponse;
import com.example.rongsok.demo.repository.ProductRepository;
import com.example.rongsok.demo.security.JwtTokenProvider;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    JwtTokenProvider tokenProvider;

    @GetMapping("/all")
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

     
     @SecurityRequirement(name = "Bearer Authentication")
     @PostMapping("/add")     
    public ResponseEntity<?> addProduct(@RequestBody @NonNull Product product, HttpServletRequest request) {
        String token = resolveToken(request);
        if (token == null || !tokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        productRepository.save(product);
        
        ProductResponse response = new ProductResponse();
        response.setMessage("Product added successfully");
        response.setProduct(product);
        return ResponseEntity.ok(response);
    
        
    }

    private String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}