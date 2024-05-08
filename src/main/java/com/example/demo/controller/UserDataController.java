package com.example.demo.controller;

import com.example.demo.entity.ForgotPassword;
import com.example.demo.entity.PinCodeDetails;
import com.example.demo.entity.SignIn;
import com.example.demo.entity.UserData;
import com.example.demo.exception.CustomException;
import com.example.demo.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/userData")
@CrossOrigin(origins = "*")
public class UserDataController {
    @Autowired
    private UserDataService userDataService;

    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/createUser")
    public ResponseEntity<UserData>createUserData(@RequestBody UserData userData) throws CustomException {
        UserData user = userDataService.createUser(userData);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<String >checkLogin(@RequestBody SignIn signIn){
        String s = userDataService.signIn(signIn);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
    @GetMapping("/{pinCode}")

    public ResponseEntity<PinCodeDetails>getPinCodeDetails(@PathVariable String pinCode) {
        String apiUrl = "https://api.postalpincode.in/pincode/"+pinCode; // Replace with your API endpoint
        PinCodeDetails[] response = restTemplate.getForEntity(apiUrl, PinCodeDetails[].class, pinCode).getBody();
//        PostOffice places = response.getPlaces().get(0);
        List<PinCodeDetails> pinCodeDetailsList = Arrays.asList(response);

        PinCodeDetails pinCodeDetails=new PinCodeDetails();
        pinCodeDetails.setStatus(pinCodeDetailsList.get(0).getStatus());
        pinCodeDetails.setPostOffice(Collections.singletonList(pinCodeDetailsList.get(0).getPostOffice().get(0)));
        return new ResponseEntity<> (pinCodeDetails,HttpStatus.OK);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<String>forgotPassword(@RequestBody ForgotPassword password) throws CustomException {
        String s = userDataService.forgotPassword(password);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
    @GetMapping("/user{email}")
    ResponseEntity<UserData>userDataByEmail(@PathVariable String email){
        UserData userData = userDataService.userDataByEmail(email);
        return new ResponseEntity<>(userData,HttpStatus.OK);
    }
}
