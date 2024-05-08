package com.example.demo.serviceImpl;

import com.example.demo.entity.Incident;
import com.example.demo.entity.UserData;
import com.example.demo.exception.CustomException;
import com.example.demo.repo.IncidentRepo;
import com.example.demo.repo.UserDataRepo;
import com.example.demo.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    private IncidentRepo incidentRepo;
    @Autowired
    private UserDataRepo userDataRepo;


    @Override
    public Incident createIncident(Incident incident) throws CustomException {

        Optional<UserData> userData = userDataRepo.findByEmailId(incident.getEmailId());
        if (userData.isEmpty()) {
            throw new CustomException("Data not present for user :" + incident.getEmailId());
        }

        incident.setIncidentId(generateString());
        incident.setUserData(userData.get());
        return incidentRepo.save(incident);
    }

    @Override
    public Incident getByIncidentId(String id) {
        return incidentRepo.findById(id).get();
    }

    @Override
    public List<Incident> listOfIncidentOfUser(String emailId) throws CustomException {
        Optional<UserData> byEmailId = userDataRepo.findByEmailId(emailId);
        int id = byEmailId.get().getId();
        List<Incident> byUserDataId = incidentRepo.findByUserDataId(id);

        return byUserDataId;
    }

    @Override
    public Incident updateIncident(Incident incident) {
        Optional<Incident> byId = Optional.empty();
        byId = incidentRepo.findById(incident.getIncidentId());
        if (byId.isPresent()) {
//            "close".equalsIgnoreCase(byId.get().getIncidentStatus());
        if (!byId.get().getIncidentStatus().equalsIgnoreCase("close")) {
                byId.get().setIncidentPriority(incident.getIncidentPriority());
                byId.get().setIncidentStatus(incident.getIncidentStatus());
                byId.get().setIdentifier(incident.getIdentifier());
                byId.get().setReporterName(incident.getReporterName());
                byId.get().setIncidentReportDate(byId.get().getIncidentReportDate());
                byId.get().setUserData(byId.get().getUserData());
                byId.get().setIncidentId(byId.get().getIncidentId());
                incidentRepo.save(byId.get());
            }
        }

        return byId.get();
    }

    public static String generateString() {
        Random random = new Random();
        int randomNum = random.nextInt(90000) + 10000;
        int currentYear = Year.now().getValue();

        String generatedString = "RMG" + randomNum + currentYear;
        System.out.println(generatedString);
        return generatedString;
    }





}
