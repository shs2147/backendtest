package com.example.demo.repo;

import com.example.demo.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDataRepo extends JpaRepository<UserData,Integer> {

    Optional<UserData> findByEmailIdAndPassword(String email, String password);

    Optional<UserData> findByEmailId(String emailId);
}
