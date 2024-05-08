package com.example.demo.entity;

import lombok.Data;

@Data
public class ForgotPassword {
    private String userName;
    private String password;
    private String confirmPassword;

}
