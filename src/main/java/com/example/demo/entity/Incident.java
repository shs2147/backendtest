package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data

public class Incident {
    @Id
    private String incidentId;
    private String identifier;
    @ManyToOne
    private UserData userData;
    private String reporterName;
    private LocalDateTime incidentReportDate = LocalDateTime.now();
    private String incidentPriority;
    private String incidentStatus;
    @Transient
    private String emailId;
}

