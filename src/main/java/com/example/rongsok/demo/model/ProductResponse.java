package com.example.rongsok.demo.model;

public class ProductResponse {
    private String message;
    private Product product;

    public ProductResponse() {
    }

    public ProductResponse(String message, Product product) {
        this.message = message;
        this.product = product;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
