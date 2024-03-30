package com.example.rongsok.demo.model;

//import javax.validation.constraints.NotBlank;

public class LoginRequest {
    //@NotBlank
    private String usernameOrEmail;

    //@NotBlank
    private String password;

    // Getters and setters...

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }
}