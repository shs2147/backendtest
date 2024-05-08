package com.example.demo.repo;

import com.example.demo.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentRepo extends JpaRepository<Incident,String> {

    List<Incident>findByUserDataId(int id);



}
