package com.example.demo.repo;

import com.example.demo.entity.RegistrationNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationNumberRepo extends JpaRepository<RegistrationNumber,Long> {
}
