package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class RegistrationNumber {
    @Id
    private Long id;
    private Long number;
}
