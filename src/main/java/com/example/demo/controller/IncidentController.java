package com.example.demo.controller;

import com.example.demo.entity.Incident;
import com.example.demo.exception.CustomException;
import com.example.demo.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incident")
@CrossOrigin(origins = "*")
public class IncidentController {
    @Autowired
    private IncidentService incidentService;

    @PostMapping("/createIncident")

    public ResponseEntity<Incident> createIncident(@RequestBody Incident incident) throws CustomException {
        return new ResponseEntity<>(incidentService.createIncident(incident), HttpStatus.OK);
    }
    @GetMapping("/{id}")

    public ResponseEntity<Incident> getByIncidentId(@PathVariable String id) throws CustomException {
        return new ResponseEntity<>(incidentService.getByIncidentId(id), HttpStatus.OK);
    }
    @GetMapping("/list/{emailId}")

    public ResponseEntity<List<Incident>> getListOfIncident(@PathVariable String emailId) throws CustomException {
        return new ResponseEntity<>(incidentService.listOfIncidentOfUser(emailId), HttpStatus.OK);
    }


    @PutMapping("update")
    public ResponseEntity<Incident>updateIncident(@RequestBody Incident incident){
        Incident incident1 = incidentService.updateIncident(incident);
        return new ResponseEntity<>(incident1,HttpStatus.OK);
    }



}
