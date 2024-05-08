package com.example.demo.service;

import com.example.demo.entity.Incident;
import com.example.demo.exception.CustomException;

import java.util.List;

public interface IncidentService {
    Incident createIncident(Incident incident) throws CustomException;
    Incident getByIncidentId (String id);
    List<Incident>listOfIncidentOfUser(String emailId) throws CustomException;

    Incident updateIncident(Incident incident);

}
