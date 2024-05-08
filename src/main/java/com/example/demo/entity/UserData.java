package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Data;



@Entity
@Data
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String emailId;
    private String phoneNumber;
    private String address;
    @Max(6)
    private Long pinCode;
    private String city;
    private String country;
    private String password;

}
