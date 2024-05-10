package com.example.demo.service;

import com.example.demo.entity.DemoData;
import com.example.demo.entity.ForgotPassword;
import com.example.demo.entity.SignIn;
import com.example.demo.entity.UserData;
import com.example.demo.exception.CustomException;

import java.util.List;

public interface UserDataService {
    UserData createUser(UserData userData) throws CustomException;

    List<UserData> getAllUserData();

    UserData getUserDataById(int userId);

    UserData updateUserData(UserData userData);

    String deleteUserData(int userId);

    String signIn(SignIn signIn);

    UserData userDataByEmail(String email);

    String forgotPassword(ForgotPassword password) throws CustomException;

    DemoData getData(int id);

    DemoData saveData(DemoData demoData);

    public String generateRegistrationNumber();
}
