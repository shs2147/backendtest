package com.example.demo.serviceImpl;

import com.example.demo.entity.*;
import com.example.demo.exception.CustomException;
import com.example.demo.external.service.DemoService;
import com.example.demo.repo.RegistrationNumberRepo;
import com.example.demo.repo.UserDataRepo;
import com.example.demo.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service

public class UserDataServiceImpl implements UserDataService {
    @Autowired
    private UserDataRepo userDataRepo;
    private final AtomicLong counter = new AtomicLong(1);

    @Autowired
    DemoService demoService;
    @Autowired
    RegistrationNumberRepo registrationNumberRepo;


    @Override
    public UserData createUser(UserData userData) throws CustomException {
        Optional<UserData> byEmailId = userDataRepo.findByEmailId(userData.getEmailId());
        if (byEmailId.isPresent()) {
            throw new CustomException("User Already Present");
        }
        return userDataRepo.save(userData);
    }

    @Override
    public List<UserData> getAllUserData() {
        return List.of();
    }

    @Override
    public UserData getUserDataById(int userId) {
        return null;
    }

    @Override
    public UserData updateUserData(UserData userData) {
        return null;
    }

    @Override
    public String deleteUserData(int userId) {
        return "";
    }

    @Override
    public String signIn(SignIn signIn) {
        Optional<UserData> byEmailIdAndPassword = userDataRepo.findByEmailIdAndPassword(signIn.getUserName(), signIn.getPassword());
        if (byEmailIdAndPassword.isEmpty()) {
            return "invalid credentials";
        }

        return "Success";
    }

    @Override
    public UserData userDataByEmail(String email) {
        Optional<UserData> byEmailId = userDataRepo.findByEmailId(email);

        return byEmailId.get();
    }

    @Override
    public String forgotPassword(ForgotPassword password) throws CustomException {
        Optional<UserData> byEmailId = userDataRepo.findByEmailId(password.getUserName());
        if (byEmailId.isPresent()) {
            if (password.getPassword().equals(password.getConfirmPassword())) {
                byEmailId.get().setPassword(password.getPassword());
                userDataRepo.save(byEmailId.get());
            } else {
                throw new CustomException("Password do no match");
            }
        } else {
            throw new CustomException("Invalid user name");
        }
        return "Password Change Successfully";
    }

    @Override
    public DemoData getData(int id) {
        return demoService.getData(id);

    }

    @Override
    public DemoData saveData(DemoData demoData) {

        return demoService.save(demoData);
    }

    private static final String PREFIX = "reg";
    private static int maxDigits = 6;

    @Override
    public String generateRegistrationNumber() {
        Optional<RegistrationNumber> optional = registrationNumberRepo.findById(1L);
        long lastNumber = optional.map(RegistrationNumber::getNumber).orElse(0L);
        long newNumber = lastNumber + 1;

        System.out.println("math" + Math.pow(10, maxDigits));


        if (newNumber > Math.pow(10, maxDigits) - 1) {
            maxDigits++;
        }

        String formattedNumber = String.format("%0" + maxDigits + "d", newNumber);
        String registrationNumber = PREFIX + formattedNumber;
        RegistrationNumber entity = new RegistrationNumber();
        entity.setId(1L);
        entity.setNumber(newNumber);
        registrationNumberRepo.save(entity);

        return registrationNumber;
    }
}

