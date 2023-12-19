package com.example.eKart.controller;

import com.example.eKart.dto.SignUpDto;
import com.example.eKart.response.ResponseClass;
import com.example.eKart.service.SignUpService;
import com.example.eKart.serviceImpl.SignUpServiceImpl;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {


    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signUp")
    public ResponseEntity<ResponseClass> signUp(@RequestBody SignUpDto signUpDto){
        return signUpService.userSignUpService(signUpDto);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseClass> login(@RequestBody SignUpDto signUpDto){
        return  signUpService.loginService(signUpDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseClass> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return signUpService.logout(token);
    }



}
