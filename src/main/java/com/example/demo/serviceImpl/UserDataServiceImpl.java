package com.example.demo.serviceImpl;

import com.example.demo.entity.ForgotPassword;
import com.example.demo.entity.SignIn;
import com.example.demo.entity.UserData;
import com.example.demo.exception.CustomException;
import com.example.demo.repo.UserDataRepo;
import com.example.demo.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserDataServiceImpl implements UserDataService {
    @Autowired
    private UserDataRepo userDataRepo;


    @Override
    public UserData createUser(UserData userData) throws CustomException {
        Optional<UserData> byEmailId = userDataRepo.findByEmailId(userData.getEmailId());
        if (byEmailId.isPresent()){
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
}
